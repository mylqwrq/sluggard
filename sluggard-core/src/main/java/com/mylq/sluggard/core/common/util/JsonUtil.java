package com.mylq.sluggard.core.common.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Json工具类
 *
 * @author WangRunQian
 * @date 2019/12/12
 * @since 1.0.0
 */
public class JsonUtil {

    private JsonUtil() {
    }

    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        return JSONObject.parseObject(jsonString, clazz);
    }

    public static String toJsonString(Object object) {
        return JSONObject.toJSONString(object);
    }
}
