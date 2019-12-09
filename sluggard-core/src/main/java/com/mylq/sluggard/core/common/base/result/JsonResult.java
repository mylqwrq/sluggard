package com.mylq.sluggard.core.common.base.result;

import com.mylq.sluggard.core.common.base.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * 返回给客户端的Json结果类
 *
 * @param <T>
 *
 * @author WangRunQian
 * @date 2019/11/28
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class JsonResult<T> implements Serializable {

    /**
     * 标识
     */
    private boolean success;
    /**
     * 信息
     */
    private String message;
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 数据
     */
    private T data;
    /**
     * 耗时
     */
    private int cost;

    public static <T> JsonResult<T> success() {
        return newSuccessJsonResult(null);
    }

    public static <T> JsonResult<T> success(T data) {
        return newSuccessJsonResult(data);
    }

    public static <T> JsonResult<T> error(Throwable e) {
        return newErrorJsonResult(Objects.isNull(e) ? HttpStatusEnum.FAILURE.getMsg() : e.getMessage());
    }

    public static <T> JsonResult<T> error(String message) {
        return newErrorJsonResult(message);
    }

    private static <T> JsonResult<T> newSuccessJsonResult(T data) {
        return newJsonResult(true, HttpStatusEnum.SUCCESS.getMsg(), HttpStatusEnum.SUCCESS.getCode(), data);
    }

    private static <T> JsonResult<T> newErrorJsonResult(String message) {
        return newJsonResult(false, message, HttpStatusEnum.FAILURE.getCode(), null);
    }

    private static <T> JsonResult<T> newJsonResult(boolean success, String message, int statusCode, T data) {
        return JsonResult.<T> builder().success(success).message(message).statusCode(statusCode).data(data).cost(0).build();
    }
}
