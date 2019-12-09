package com.mylq.sluggard.core.common.util;

import java.lang.reflect.Field;

/**
 * 反射工具类
 *
 * @author WRQ
 * @date 2019/12/4
 * @since 1.0.0
 */
public class ReflectUtil {

    private ReflectUtil() {
    }

    /**
     * 获取对象类型的所有Field
     *
     * @param clazz 对象类型
     * @return Field数组
     */
    public static Field[] getFields(Class clazz) {
        return clazz.getDeclaredFields();
    }
}
