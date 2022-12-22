package com.joyou.patrol.enums;

/**
 * Created by geely
 */
public enum ResponseCode {

    SUCCESS(200,"SUCCESS"),
    ERROR(300,"ERROR"),

    INSUFFICIENT_INVENTORY(10001,"库存不足")
    ;

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
