package com.neu.exception.handler;

import com.neu.common.ExceptionResponse;
import com.neu.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * Created by mark on 17/11/5.
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public ExceptionResponse handle(BaseException err) {
        return new ExceptionResponse(err.getCode(),err.getMessage());
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class,
            MultipartException.class,
    })
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public ExceptionResponse handleInner(Throwable throwable) {
        return new ExceptionResponse(500,throwable.getMessage());
    }
}
