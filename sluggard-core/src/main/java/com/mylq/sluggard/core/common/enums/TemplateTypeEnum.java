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
 * 模板类型枚举类
 *
 * @author WangRunQian
 * @date 2019/12/27
 * @since 1.0.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@JsonSerialize(using = BaseEnumSerializer.class)
public enum TemplateTypeEnum implements BaseEnum<Integer> {

    /**
     * 代码
     */
    CODE(0, "code"),
    /**
     * 项目
     */
    PROJECT(1, "project");

    private Integer id;
    private String name;

    @JsonCreator
    public static TemplateTypeEnum get(Integer id) {
        for (TemplateTypeEnum item : values()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new SluggardCoreException("Unsupported template type: {0}.", id);
    }

    public static TemplateTypeEnum get(String name) {
        for (TemplateTypeEnum item : values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new SluggardCoreException("Unsupported template type: {0}.", name);
    }
}
