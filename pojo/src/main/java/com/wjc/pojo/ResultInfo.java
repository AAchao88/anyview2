package com.wjc.pojo;

/**
 * 封装回显信息
 */

public class ResultInfo {
    //返回操作是否成功
    private Boolean success;
    //返回结果信息
    private String message;
    //返回数据
    private Object data;

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}

