package com.joyou.patrol.exception;

import lombok.Data;

/**
 * @author HuanyuZhou
 * @date 2022/3/1 9:26
 */
@Data
public class MobileServiceException extends RuntimeException {

    private Integer code = 202;

    /**
     * 默认业务错误msg
     */
    private String msg = "system service exception!";

    public MobileServiceException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MobileServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
