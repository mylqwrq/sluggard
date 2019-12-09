package com.mylq.sluggard.core.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 列信息实体类
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ColumnEntity implements Serializable {

    /**
     * 列名
     */
    private String columnName;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 缺省值
     */
    private String columnDefault;
    /**
     * 是否允许为空
     */
    private String isNullable;
    /**
     * 长度
     */
    private String columnLength;
    /**
     * 主键
     */
    private String columnKey;
    /**
     * 备注
     */
    private String columnComment;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * Java类型
     */
    private String javaType;
    /**
     * Jdbc类型
     */
    private String jdbcType;
}
