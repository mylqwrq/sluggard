package ${project.basePackage}.core.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ${project.basePackage}.core.util.HttpUtil;

public class BaseHandlerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BaseHandlerInterceptor.class);
    private static final ThreadLocal<Long> START_TIME_THREAD_LOCAL = new NamedThreadLocal<>("startTimeThreadLocal");
    private static final long SLOW_THRESHOLD = 200;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        START_TIME_THREAD_LOCAL.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        long cost = System.currentTimeMillis() - START_TIME_THREAD_LOCAL.get();
        if (cost >= SLOW_THRESHOLD) {
            logger.warn("接口响应缓慢：url = {}, ip = {}, cost = {}ms.", request.getRequestURI(), HttpUtil.getIpAddr(request), cost);
        }
        START_TIME_THREAD_LOCAL.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
