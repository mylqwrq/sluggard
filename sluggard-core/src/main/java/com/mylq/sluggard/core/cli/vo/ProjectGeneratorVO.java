package com.mylq.sluggard.core.cli.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 项目生成视图对象
 *
 * @author WangRunQian
 * @date 2019/12/26
 * @since 1.0.0
 */
@Data
public class ProjectGeneratorVO implements Serializable {

    private String templateNames;

    private String templateConfigs;

    private String project;

    private String dataSource;

    private String tables;

    private Boolean lombokFlag;

    private Boolean swaggerFlag;
}
