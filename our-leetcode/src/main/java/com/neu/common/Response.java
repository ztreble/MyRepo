package com.neu.common;

/**
 * Created by mark on 17/10/31.
 */
public class Response<T> {
    private int code;

    private Object data;

    public Response(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Response() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
