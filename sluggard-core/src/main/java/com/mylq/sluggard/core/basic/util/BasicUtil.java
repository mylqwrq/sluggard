package com.mylq.sluggard.core.basic.util;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.mylq.sluggard.core.common.base.constant.Constant;

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

    public static String propKeyFormat(String... keyParts) {
        return Joiner.on(Constant.PROP_KEY_SEPARATOR).useForNull("").join(keyParts);
    }

    public static String propValueFormat(String... valueParts) {
        return Joiner.on(Constant.PROP_VALUE_SEPARATOR).useForNull("").join(valueParts);
    }

    public static void checkPropKeyPart(String name, String str) {
        if (Objects.nonNull(str) && str.contains(Constant.PROP_KEY_SEPARATOR)) {
            throw new IllegalArgumentException(MessageFormat.format("Parameter {0} must not contain '{1}'.", name, Constant.PROP_KEY_SEPARATOR));
        }
    }

    public static void checkPropValuePart(String name, String str) {
        if (Objects.nonNull(str) && str.contains(Constant.PROP_VALUE_SEPARATOR)) {
            throw new IllegalArgumentException(MessageFormat.format("Parameter {0} must not contain '{1}'.", name, Constant.PROP_VALUE_SEPARATOR));
        }
    }

    public static void requireNotNullOrBlank(String name, String str) {
        if (Strings.isNullOrEmpty(str) || str.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageFormat.format("Parameter {0} must not be null or blank.", name));
        }
    }

    public static void requireNotNull(String name, Object obj) {
        if (Objects.isNull(obj)) {
            throw new NullPointerException(MessageFormat.format("Parameter {0} must not be null.", name));
        }
    }
}
