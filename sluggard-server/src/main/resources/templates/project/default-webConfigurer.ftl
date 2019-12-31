package ${project.basePackage}.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mylq.core.base.interceptor.CostHandlerInterceptor;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CostHandlerInterceptor()).addPathPatterns("/**");
    }
}