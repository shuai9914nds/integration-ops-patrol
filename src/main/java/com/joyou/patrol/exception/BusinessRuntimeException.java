package com.joyou.patrol.exception;

import lombok.Data;

@Data
public class BusinessRuntimeException extends RuntimeException{

    private Integer code;
    private String msg;

    public BusinessRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
}
