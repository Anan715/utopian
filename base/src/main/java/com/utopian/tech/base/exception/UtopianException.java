package com.utopian.tech.base.exception;

import com.utopian.tech.base.errorEnum.UtopianErrorEnum;

public class UtopianException extends Exception {

    private static final long serialVersionUID = -1;

    private int code;

    public UtopianException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /*** 错误码枚举*/
    private Enum<?> errorType;

    /**
     * 带参构造器.
     */
    public UtopianException(UtopianErrorEnum errorEnum) {
        super();
        this.errorType = errorEnum;
    }


    /**
     * Gets error code.
     *
     * @return the error code
     */
    public Enum<?> getErrorType() {
        return errorType;
    }



}
