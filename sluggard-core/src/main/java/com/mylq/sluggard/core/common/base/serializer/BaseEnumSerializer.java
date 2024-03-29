package com.mylq.sluggard.core.common.base.serializer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mylq.sluggard.core.common.base.enums.BaseEnum;
import com.mylq.sluggard.core.common.base.exception.SluggardCoreException;

/**
 * 枚举序列化
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
public class BaseEnumSerializer extends JsonSerializer<BaseEnum> {

    @Override
    public void serialize(BaseEnum enumObj, JsonGenerator jg, SerializerProvider provider) throws IOException {
        try {
            jg.writeStartObject();
            Method[] methods = enumObj.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getParameterTypes().length != 0) {
                    continue;
                }
                String methodName = m.getName();
                //if (methodName.startsWith("get")) {
                if ("getId".equals(methodName) || "getName".equals(methodName)) {
                    String fieldName = methodName.substring(3).toLowerCase();
                    jg.writeStringField(fieldName, m.invoke(enumObj).toString());
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new SluggardCoreException(e);
        } finally {
            jg.writeEndObject();
        }
    }
}
