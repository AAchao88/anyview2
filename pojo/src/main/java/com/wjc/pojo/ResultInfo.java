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

    private Object data2;

    private Object data3;

    public Object getData3() {
        return data3;
    }

    public void setData3(Object data3) {
        this.data3 = data3;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

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

