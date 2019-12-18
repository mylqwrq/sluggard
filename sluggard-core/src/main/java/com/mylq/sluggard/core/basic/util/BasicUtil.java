package com.mylq.sluggard.core.basic.util;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Basic工具类
 *
 * @author WangRunQian
 * @date 2019/12/7
 * @since 1.0.0
 */
public class BasicUtil {

    private BasicUtil() {
    }

    public static void requireNotNull(String name, Object obj) {
        if (Objects.isNull(obj)) {
            throw new NullPointerException(MessageFormat.format("Parameter {0} must not be null.", name));
        }
    }

    public static void requireNotNullOrBlank(String name, String str) {
        if (Objects.isNull(str) || str.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageFormat.format("Parameter {0} must not be null or blank.", name));
        }
    }
}
