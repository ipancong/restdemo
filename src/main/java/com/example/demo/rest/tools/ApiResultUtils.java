package com.example.demo.rest.tools;

import org.springframework.http.HttpStatus;

public class ApiResultUtils {
    public static <T> ApiResult<T> success(T data) {
        return commonResult(String.valueOf(HttpStatus.OK.value()), "success", data);
    }

    public static <T> ApiResult<T> error(String errorMsg) {
        return error(HttpStatus.OK.value(), errorMsg);
    }

    public static <T> ApiResult<T> error(Integer code, String errorMsg) {
        return commonResult(String.valueOf(code), errorMsg, null);
    }

    public static <T> ApiResult<T> error(String code, String errorMsg) {
        return commonResult(code, errorMsg, null);
    }

    private static <T> ApiResult<T> commonResult(String code, String message, T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
