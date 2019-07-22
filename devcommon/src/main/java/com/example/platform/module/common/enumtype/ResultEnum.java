package com.example.platform.module.common.enumtype;

public enum ResultEnum {

    UNKOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    SITUATION_ONE(10, "情况1"),
    SITUATION_TWO(16, "情况2"),;

    private Integer code;

    private String msg;

    private ResultEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

}