package com.mylq.sluggard.core.common.base.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Http状态码枚举类
 *
 * @author WangRunQian
 * @date 2019/11/28
 * @since 1.0.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum HttpStatusEnum implements Serializable {

    SUCCESS(200, "操作成功！"), FAILURE(500, "操作失败！");

    private int code;
    private String msg;
}
