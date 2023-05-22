package com.mylq.sluggard.core.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mylq.sluggard.core.common.base.enums.BaseEnum;
import com.mylq.sluggard.core.common.base.exception.SluggardCoreException;
import com.mylq.sluggard.core.common.base.serializer.BaseEnumSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 文件类型枚举类
 *
 * @author WangRunQian
 * @date 2019/12/5
 * @since 1.0.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@JsonSerialize(using = BaseEnumSerializer.class)
public enum FileTypeEnum implements BaseEnum<Integer> {

    /**
     * FTL
     */
    FTL(0, ".ftl"),
    /**
     * JAVA
     */
    JAVA(1, ".java"),
    /**
     * XML
     */
    XML(2, ".xml"),
    /**
     * YML
     */
    YML(3, ".yml"),
    /**
     * HTML
     */
    HTML(4, ".html"),
    /**
     * JS
     */
    JS(5, ".js"),
    /**
     * properties
     */
    PROP(6, ".properties");

    private Integer id;
    private String name;

    @JsonCreator
    public static FileTypeEnum get(Integer id) {
        for (FileTypeEnum item : values()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new SluggardCoreException("Unsupported file type: {0}.", id);
    }

    public static FileTypeEnum get(String name) {
        for (FileTypeEnum item : values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new SluggardCoreException("Unsupported file type: {0}.", name);
    }
}
