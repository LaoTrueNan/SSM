package com.byd.gzq.utils.Exception;

/**
 * @author 4466184
 * @date 2022/9/22 10:18
 */

public class ServiceException extends Exception{

    private String message;
    private int code;

    public ServiceException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
