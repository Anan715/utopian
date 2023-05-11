package com.utopian.tech.base.errorEnum;

public enum UtopianErrorEnum {

    REQUEST_REPEAT_ERROR("请勿重复提交", 10001),
    REQUEST_LACK_PARAM_ERROR("缺少必要参数", 10002),
    REQUEST_OPERATED_ERROR("缺少必要参数", 10003);

    private String name;
    private int code;

    private UtopianErrorEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static UtopianErrorEnum getError(int code) {
        for (UtopianErrorEnum error : UtopianErrorEnum.values()) {
            if (error.getCode() == code) {
                return error;
            }
        }
        return null;
    }
}
