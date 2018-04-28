package com.framework.core.network.retrofit.callback;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

/**
 * Created by laulee on 2018/4/28.
 */

public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof ApiException) {             //HTTP错误
            ex = (ApiException) e;
            switch (((ApiException) e).getCode()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setMessage("服务器错误");//均视为网络错误
                    break;
            }
            return ex;
        } else
            if (e instanceof RuntimeException) {    //服务器返回的错误
            RuntimeException resultException = (RuntimeException) e;
            ex = new ApiException(ErrorCode.RUNTIME_ERROR, resultException.getMessage());
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof
                ParseException) {
            ex = new ApiException(ErrorCode.PARSE_ERROR, "数据解析错误");
            //均视为解析错误
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(ErrorCode.NETWORD_ERROR, "网络连接失败");
            //均视为网络错误
            return ex;
        } else {
            ex = new ApiException(ErrorCode.UNKNOWN, "未知错误");
            //未知错误
            return ex;
        }
    }
}
