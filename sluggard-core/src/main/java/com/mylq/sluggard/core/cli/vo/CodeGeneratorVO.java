package com.mylq.sluggard.core.cli.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 代码生成视图对象
 *
 * @author WangRunQian
 * @date 2019/12/17
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CodeGeneratorVO implements Serializable {

    private String templateNames;

    private String configs;

    private String table;

    private String columns;
}
