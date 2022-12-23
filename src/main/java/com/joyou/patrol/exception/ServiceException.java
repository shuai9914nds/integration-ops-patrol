package com.joyou.patrol.exception;

import lombok.Data;

/**
 * <p>
 * 服务异常
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@Data
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 857100357560593140L;
    /**
     * 默认业务错误码
     */
    private int code = 10000;

    /**
     * 默认业务错误msg
     */
    private String msg = "system service exception!";

    public ServiceException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
