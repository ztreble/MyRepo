package com.neu.exception.general;

import com.neu.exception.BaseException;

/**
 * @author: treblez
 * @className: TokenInvalidException
 * @description: 定义Token无效异常
 * @data: 2020-04-05
 **/

public class TokenInvalidException extends BaseException {
    public TokenInvalidException() {
        this.setCode(20002);
        this.setMessage("Token无效！");
    }
}
