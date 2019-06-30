package com.example.demo.rest.tools;

import lombok.Data;

@Data
public class ApiResult<T> {
    /**
     * 错误码
     */
    private String code;
    /**
     * 对错误的具体解释
     */
    private String message;
    /**
     * 返回的结果包装在data中，返回前台为json数据
     */
    private  T data;

    /**
     * 错误码一般用String类型，这里也兼容一下int类型的
     * @param code
     */
    public void setCode(int code){
        this.code = String.valueOf(code);
    }

    public void setCode(String code){
        this.code = code;
    }
}
