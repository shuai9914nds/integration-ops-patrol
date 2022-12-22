package com.joyou.patrol.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joyou.patrol.enums.ResponseCode;

import java.io.Serializable;

/**
 * Candy
 */
//保证序列化json的时候,如果是null的对象,key也会消失
//@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int code;
    private String message;
    private String errorMessage;
    private T data;

    private ServerResponse() {
    }

    private ServerResponse(int code) {
        this.code = code;
    }

    private ServerResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //使之不在json序列化结果当中
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String message) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String message, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message, data);
    }


    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }


    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, T data) {
        return new ServerResponse<T>(errorCode, data);
    }

    @Override
    public String toString() {
        return "ServerResponse{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
