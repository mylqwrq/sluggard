package com.mylq.sluggard.core.cli.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Config视图对象
 *
 * @author WangRunQian
 * @date 2019/12/13
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ConfigVO implements Serializable {

    private String key;

    private String value;
}
