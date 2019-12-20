package com.mylq.sluggard.core.db.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

/**
 * 列信息实体类
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
@Data
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

    /**
     * 获取列类型
     */
    public String getColumnType() {
        return Objects.isNull(javaType) ? null : javaType.substring(javaType.lastIndexOf(".") + 1);
    }
}
