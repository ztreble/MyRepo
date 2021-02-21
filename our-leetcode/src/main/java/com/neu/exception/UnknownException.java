package com.neu.exception;

public class UnknownException extends BaseException{
    public UnknownException(String message) {
        this.setCode(300);
        this.setMessage(message);
    }
}
