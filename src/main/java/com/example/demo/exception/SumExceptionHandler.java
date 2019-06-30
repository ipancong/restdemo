package com.example.demo.exception;

import com.example.demo.I18nService;
import com.example.demo.common.ErrorCode;
import com.example.demo.rest.tools.ApiResult;
import com.example.demo.rest.tools.ApiResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class SumExceptionHandler {

    /**
     * 默认错误码
     */
    private String code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    private String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    @Autowired
    private I18nService i18nService;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> ApiResult<T> handle(Exception e, HttpServletRequest request) {
        if (e instanceof SumException) {

            SumException exception = (SumException) e;
            if (Integer.valueOf(exception.getCode()) != HttpStatus.OK.value()) {
                code = exception.getCode();
                String msg = i18nService.getMessage(request, ErrorCode.SAVE_ERROR);
                return ApiResultUtils.error(code, msg);
            }

        }
        return ApiResultUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage() == null ? INTERNAL_SERVER_ERROR : e.getMessage());
    }

}
