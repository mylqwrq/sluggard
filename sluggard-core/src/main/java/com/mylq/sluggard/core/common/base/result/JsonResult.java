package com.mylq.sluggard.core.common.base.result;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
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
        return newErrorJsonResult(Objects.isNull(e) ? "操作失败！" : e.getMessage());
    }

    public static <T> JsonResult<T> error(String message) {
        return newErrorJsonResult(message);
    }

    private static <T> JsonResult<T> newSuccessJsonResult(T data) {
        return newJsonResult(true, "操作成功！", 200, data);
    }

    private static <T> JsonResult<T> newErrorJsonResult(String message) {
        return newJsonResult(false, message, 500, null);
    }

    private static <T> JsonResult<T> newJsonResult(boolean success, String message, int statusCode, T data) {
        return JsonResult.<T> builder().success(success).message(message).statusCode(statusCode).data(data).cost(0).build();
    }
}
