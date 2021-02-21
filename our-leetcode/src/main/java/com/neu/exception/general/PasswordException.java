package com.neu.exception.general;

import com.neu.exception.BaseException;

/**
 * @author: treblez
 * @className: PasswordException
 * @description: 自定义密码错误异常处理
 * @data: 2020-04-05
 **/

public class PasswordException extends BaseException {
    public PasswordException() {
        this.setCode(20001);
        this.setMessage("密码错误");
    }
}
