package com.mylq.sluggard.core.common.base.enums;

import java.io.Serializable;

/**
 * 基本枚举接口
 *
 * @param <K> ID的类型
 *
 * @author WangRunQian
 * @date 2019/11/29
 * @since 1.0.0
 */
public interface BaseEnum<K> extends Serializable {

    K getId();

    String getName();
}
