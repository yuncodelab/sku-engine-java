package com.yuncodelab.sku.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    /**
     * 通用错误处理，默认错误码 500
     */
    public static <T> Result<T> error(String message) {
        return error(500, message);
    }

    /**
     * 自定义错误码和信息的错误处理
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null); // 错误时通常 data 为空
        return result;
    }
}
