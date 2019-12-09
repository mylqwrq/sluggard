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
    FTL(0, ".ftl", "Ftl"),
    /**
     * JAVA
     */
    JAVA(1, ".java", "Java"),
    /**
     * XML
     */
    XML(2, ".xml", "Xml"),
    /**
     * YML
     */
    YML(3, ".yml", "Yml"),
    /**
     * HTML
     */
    HTML(4, ".html", "Html"),
    /**
     * JS
     */
    JS(5, ".js", "Js");

    private Integer id;
    private String name;
    private String option;

    @JsonCreator
    public static FileTypeEnum get(Integer id) {
        for (FileTypeEnum item : values()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new SluggardCoreException("Unsupported fileType: {0}.", id);
    }
}
