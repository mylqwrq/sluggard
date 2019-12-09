package com.mylq.sluggard.core.basic.vo;

import com.mylq.sluggard.core.common.enums.FileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
@Getter
@Setter
@ToString
public class TemplateVO implements Serializable {

    private String name;

    private FileTypeEnum fileType;

    private String fileRelativePath;

    private String fileNamePrefix;

    private String fileNameSuffix;

    private String context;
}
