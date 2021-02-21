package com.neu.exception.general;


import com.neu.exception.BaseException;
import org.springframework.validation.BindingResult;

/**
 * @author: treblez
 * @className: FormValidatorException
 * @description: 定义参数校验异常
 * @data: 2020-04-05
 **/

public class FormValidatorException extends BaseException {
    public FormValidatorException(BindingResult bindingResult) {
        this.setMessage(bindingResult.getAllErrors().toString());
        this.setCode(40001);
    }

}
