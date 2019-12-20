package com.mylq.sluggard.core.cli.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 代码生成视图对象
 *
 * @author WangRunQian
 * @date 2019/12/17
 * @since 1.0.0
 */
@Data
public class CodeGeneratorVO implements Serializable {

    private String templateNames;

    private String templateConfigs;

    private String project;

    private String dataSource;

    private String table;

    private String columns;
}
