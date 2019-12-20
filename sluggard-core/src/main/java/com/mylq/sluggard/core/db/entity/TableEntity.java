package com.mylq.sluggard.core.db.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 表信息实体类
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
@Data
public class TableEntity implements Serializable {

    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表备注
     */
    private String tableComment;
    /**
     * 模块名称
     */
    private String moduleName;
}
