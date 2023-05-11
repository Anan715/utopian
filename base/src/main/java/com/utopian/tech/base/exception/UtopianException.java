package com.utopian.tech.base.exception;

public class UtopianException extends Exception {

    private static final long serialVersionUID = -1;

    /*** 错误码枚举*/
    private Enum<?> errorType;

    /**
     * 带参构造器.
     */
    public UtopianException(Enum<?> error) {
        super();
        this.errorType = error;
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
