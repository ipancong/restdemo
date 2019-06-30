package com.example.demo.exception;

import com.example.demo.I18nService;
import com.example.demo.common.ErrorCode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Data
public class SumException extends RuntimeException {

    // 错误信息
    private String errorMsg;
    // 错误码
    private String code;

    public SumException(String code) {
        this.code = code;
    }

}

