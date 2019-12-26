package com.mylq.sluggard.core.basic.vo;

import java.io.Serializable;

import com.mylq.sluggard.core.common.enums.FileTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Template视图对象
 *
 * @author WangRunQian
 * @date 2019/12/7
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TemplateVO implements Serializable {

    private String name;

    private FileTypeEnum fileType;

    private String fileRelativePath;

    private String fileNamePrefix;

    private String fileNameSuffix;

    private String context;

    private Boolean disabled;
}
