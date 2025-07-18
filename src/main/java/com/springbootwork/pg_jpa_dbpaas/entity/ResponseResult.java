package com.springbootwork.pg_jpa_dbpaas.entity;

import lombok.Data;

/**
 * API接口统一返回类
 */
@Data

public class ResponseResult<T> {

    private Integer code;
    private String message;
    private Object data;

    public static ResponseResult success(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResultCode.SUCCESS.getCode());
        responseResult.setMessage(ResultCode.SUCCESS.getMessage());
        return responseResult;
    }

    public static ResponseResult success(Object data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResultCode.SUCCESS.getCode());
        responseResult.setMessage(ResultCode.SUCCESS.getMessage());
        responseResult.setData(data);
        return responseResult;
    }

    public static ResponseResult faild(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResultCode.FAILD.getCode());
        responseResult.setMessage(ResultCode.FAILD.getMessage());
        return responseResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
