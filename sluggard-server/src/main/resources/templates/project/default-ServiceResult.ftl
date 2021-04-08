package ${project.basePackage}.core.base.result;

import java.io.Serializable;

public class ServiceResult<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    private long total;

    private int pageNum;

    private int pageSize;

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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public static <T> ServiceResult<T> success(T data) {
        return success(data, 0, 0, 0);
    }

    public static <T> ServiceResult<T> success(T data, long total, int pageNum, int pageSize) {
        return create(0, null, data, total, pageNum, pageSize);
    }

    public static <T> ServiceResult<T> error(String message) {
        return error(1, message);
    }

    public static <T> ServiceResult<T> error(int code, String message) {
        return create(code, message, null, 0, 0, 0);
    }

    private static <T> ServiceResult<T> create(int code, String message, T data, long total, int pageNum, int pageSize) {
        ServiceResult<T> serviceResult = new ServiceResult<>();
        serviceResult.setCode(code);
        serviceResult.setMessage(message);
        serviceResult.setData(data);
        serviceResult.setTotal(total);
        serviceResult.setPageNum(pageNum);
        serviceResult.setPageSize(pageSize);
        return serviceResult;
    }
}
