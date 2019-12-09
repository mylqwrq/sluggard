package com.mylq.sluggard.core.basic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Mapping视图对象
 *
 * @author WangRunQian
 * @date 2019/12/5
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MappingVO implements Serializable {

    private String dbName;

    private String dataType;

    private String javaType;

    private String jdbcType;
}
