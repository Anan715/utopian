package com.utopian.tech.base.response;

import org.springframework.http.HttpStatus;

public class UtopianResponse<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    private int status;
    private String message;
    private T data;

    private UtopianResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> UtopianResponse<T> successWithoutData() {
        return new UtopianResponse<>(HttpStatus.OK.value(), null, null);
    }

    public static <T> UtopianResponse<T> successWithoutData(String message) {
        return new UtopianResponse<>(HttpStatus.OK.value(), message, null);
    }

    public static <T> UtopianResponse<T> successWithData(T data) {
        return new UtopianResponse<>(HttpStatus.OK.value(), null, data);
    }

    public static <T> UtopianResponse<T> failureWithoutException(String message) {
        return new UtopianResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    public static <T> UtopianResponse<T> failureWithException(Throwable throwable) {
        return new UtopianResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), throwable.getMessage(), null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}