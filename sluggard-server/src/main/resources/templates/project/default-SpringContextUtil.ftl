package ${project.basePackage}.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

    private SpringContextUtil() {
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        com.lvyue.switching.core.util.SpringContextUtil.applicationContext = applicationContext;
    }  

    public static ApplicationContext getApplicationContext() {
        return applicationContext;  
    }  

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);  
    }

    public static <T> T getBean(Class<T> var1) throws BeansException {
        return applicationContext.getBean(var1);
    }
}