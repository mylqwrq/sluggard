package ${project.basePackage}.core.util;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    private ServletUtil() {
    }

    public static boolean isBlank(String name) {
        return name == null || name.isEmpty() || name.trim().length() == 0;
    }

    public static String getRequestHeaderAttribute(HttpServletRequest httpRequest, String name) {
        if (httpRequest == null || isBlank(name)) {
            return null;
        }
        return httpRequest.getHeader(name);
    }

    public static String getCookie(HttpServletRequest httpRequest, String name) {
        if (httpRequest == null || isBlank(name)) {
            return null;
        }
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie != null && name.equalsIgnoreCase(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static String expandUrl(HttpServletRequest request, String url) {
        if (request == null || url == null) {
            return url;
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        StringBuilder sb = new StringBuilder();
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        if (requestParameterMap != null) {
            for (Map.Entry<String, String[]> entry : requestParameterMap.entrySet()) {
                if (!"logType".equalsIgnoreCase(entry.getKey()) && entry.getValue() != null && entry.getValue().length > 0) {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue()[0]);
                }
            }
        }
        String parameters = sb.toString();
        return parameters.length() == 0 ? url : url + parameters.replaceFirst("&", "?");
    }
}
