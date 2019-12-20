package com.mylq.sluggard.core.cli.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 模板属性视图对象
 *
 * @author WangRunQian
 * @date 2019/12/13
 * @since 1.0.0
 */
@Data
public class TemplateConfigVO implements Serializable {

    private String key;

    private String value;
}
