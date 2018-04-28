package com.framework.core.network.retrofit.callback;

/**
 * Created by laulee on 2018/4/28.
 */

public class ApiException extends Exception {
    private String message;
    private int code;

    public ApiException( int code, String message ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
