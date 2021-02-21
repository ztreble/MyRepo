package com.neu.exception.general;

import com.neu.exception.BaseException;

/**
 * @author: treblez
 * @className: ResourceNotExistException
 * @description: 定义资源不存在异常
 * @data: 2020-04-05
 **/

public class ResourceNotExistException extends BaseException {
    private String resourceName;

    public ResourceNotExistException(String resourceName) {
        this.setCode(40002);
        this.resourceName = resourceName;
        this.setMessage(resourceName + "资源不存在！");
    }
}
