package com.common.org.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData<T> {

    private static final int SUCCESS = 200;
    private static final int ERROR = 400;
    private static final String MESSAGE = "success";

    private int code;
    private String message;
    private T result;

    public ResultData() {
        this.code = SUCCESS;
        this.message = "";
        this.result = null;
    }

    public ResultData(int code, String message, T result) {
        this.code = code;

        this.message = message;
        this.result = result;
    }

    public static <T> ResultData<T> newSuccess(int code, String message, T result) {
        return new ResultData<T>(code, message, result);
    }

    public static <T> ResultData<T> newSuccess(ResponseCode response, T result) {
        return new ResultData<>(response.code, response.message, result);
    }

    public static <T> ResultData<T> newSuccess(String message, T result) {
        return new ResultData<>(SUCCESS, message, result);
    }

    public static <T> ResultData<T> newSuccess(T result) {
        return new ResultData<>(SUCCESS, MESSAGE, result);
    }

    public static <T> ResultData<T> newError(int code, String message) {
        return new ResultData<>(code, message, null);
    }

    public static <T> ResultData<T> newError(ResponseCode response) {
        return new ResultData<>(response.code, response.message, null);
    }
}