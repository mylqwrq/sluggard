package com.mylq.sluggard.core.cli.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 项目基本信息视图对象
 *
 * @author WangRunQian
 * @date 2019/12/20
 * @since 1.0.0
 */
@Data
public class ProjectBasicVO implements Serializable {

    private String name;

    private String groupId;

    private String artifactId;

    private String version;

    private String packaging;

    private String description;

    private String basePackage;

    private String serverPort;

    private String serverPath;
}
