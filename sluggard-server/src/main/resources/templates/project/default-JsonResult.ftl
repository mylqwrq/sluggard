package ${project.basePackage}.core.base.result;

import java.io.Serializable;

import ${project.basePackage}.core.base.enums.BaseEnum;

public class JsonResult<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    private long total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public static <T> JsonResult<T> success(T data) {
        return success(data, 0);
    }

    public static <T> JsonResult<T> success(T data, long total) {
        return create(0, null, data, total);
    }

    public static <T> JsonResult<T> error(String message) {
        return error(1, message);
    }

    public static <T> JsonResult<T> error(BaseEnum error) {
        return error(error.getCode(), error.getName());
    }

    public static <T> JsonResult<T> error(int code, String message) {
        return create(code, message, null, 0);
    }

    public static <T> JsonResult<T> transmit(ServiceResult<T> serviceResult) {
        return create(serviceResult.getCode(), serviceResult.getMessage(), serviceResult.getData(), serviceResult.getTotal());
    }

    private static <T> JsonResult<T> create(int code, String message, T data, long total) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        jsonResult.setData(data);
        jsonResult.setTotal(total);
        return jsonResult;
    }
}