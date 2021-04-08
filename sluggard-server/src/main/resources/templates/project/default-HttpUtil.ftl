package ${project.basePackage}.core.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    private HttpUtil() {
    }

    private static final String UNKNOWN_HOST = "unknown";
    private static final String LOCAL_HOST_IP_V4 = "127.0.0.1";
    private static final String LOCAL_HOST_IP_V6 = "0:0:0:0:0:0:0:1";

    /**
     * 从Request中获取请求IP
     *
     * @param request 请求
     * @return 请求IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN_HOST.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN_HOST.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN_HOST.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCAL_HOST_IP_V4.equals(ipAddress) || LOCAL_HOST_IP_V6.equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException ignored) {
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0];
        }
        return ipAddress;
    }

    /**
     * 执行 Get 请求
     *
     * @param url 请求地址
     * @return 返回结果
     */
    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    /**
     * 执行 Get 请求
     *
     * @param url      请求地址
     * @param paramMap 请求参数集合
     * @return 返回结果
     */
    public static String doGet(String url, Map<String, Object> paramMap) {
        return doGet(url, paramMap, null);
    }

    /**
     * 执行 Get 请求
     *
     * @param url       请求地址
     * @param paramMap  请求参数集合
     * @param headerMap 请求头信息集合
     * @return 返回结果
     */
    public static String doGet(String url, Map<String, Object> paramMap, Map<String, String> headerMap) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            List<NameValuePair> params = new ArrayList<>();
            if (paramMap != null && !paramMap.isEmpty()) {
                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                    params.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                }
            }
            URI uri = new URIBuilder().setPath(url).setParameters(params).build();

            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 请求信息
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(requestConfig);
            if (headerMap != null && !headerMap.isEmpty()) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // 发送请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            return null;
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
