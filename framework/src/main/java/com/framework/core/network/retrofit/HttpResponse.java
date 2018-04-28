package com.framework.core.network.retrofit;

/**
 * Created by laulee on 2018/4/28.
 */

public class HttpResponse {

    public int code;
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }
}
