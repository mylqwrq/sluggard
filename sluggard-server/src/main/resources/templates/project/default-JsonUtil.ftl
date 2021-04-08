package ${project.basePackage}.core.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    private JsonUtil() {
    }

    public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
        if (jsonString == null || jsonString.length() == 0 || clazz == null) {
            return null;
        }
        return JSONArray.parseArray(jsonString, clazz);
    }

    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if (jsonString == null || jsonString.length() == 0 || clazz == null) {
            return null;
        }
        return JSONObject.parseObject(jsonString, clazz);
    }

    public static Map<String, Object> parseObject(String jsonString) {
        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }
        return JSONObject.parseObject(jsonString);
    }

    public static String toJsonString(Object object) {
        return object == null ? null : JSONObject.toJSONString(object);
    }
}
