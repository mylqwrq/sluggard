package com.mylq.sluggard.core.basic.vo;

import java.io.Serializable;

import com.mylq.sluggard.core.common.enums.DbTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Mapping视图对象
 *
 * @author WangRunQian
 * @date 2019/12/5
 * @since 1.0.0
 */
@AllArgsConstructor
@Builder
@Data
public class MappingVO implements Serializable {

    private DbTypeEnum dbType;

    private String dataType;

    private String javaType;

    private String jdbcType;
}
