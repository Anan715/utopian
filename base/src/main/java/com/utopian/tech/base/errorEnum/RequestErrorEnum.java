package com.utopian.tech.base.errorEnum;

public enum RequestErrorEnum {

    REQUEST_REPEAT_ERROR("请勿重复提交", 10001),
    REQUEST_LACK_PARAM_ERROR("缺少必要参数", 10002);

    private String name;
    private int code;

    private RequestErrorEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static RequestErrorEnum getError(int code) {
        for (RequestErrorEnum error : RequestErrorEnum.values()) {
            if (error.getCode() == code) {
                return error;
            }
        }
        return null;
    }
}
