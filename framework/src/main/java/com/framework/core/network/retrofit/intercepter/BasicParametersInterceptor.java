package com.framework.core.network.retrofit.intercepter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.framework.core.utils.AppUtils;
import com.framework.core.utils.DeviceUtil;
import com.framework.core.utils.IntenetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by laulee on 2018/4/28.
 */

public class BasicParametersInterceptor implements Interceptor {

    private Context context;

    public BasicParametersInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        if (request.method().equals("GET")) {

            HttpUrl newHttpUrl = getHttpUrl(request);
            requestBuilder = request.newBuilder().url(newHttpUrl);

        } else if (request.method().equals("POST")) {

            RequestBody requestBody = request.body();
            if (requestBody != null) {
                String requestBodyStr = bodyToString(requestBody);
                Map paramMap = JSONObject.parseObject(requestBodyStr);
                if (requestBody instanceof MultipartBody) {
                    requestBuilder = request.newBuilder().post(requestBody);
                } else {
                    FormBody formBody = getFormBody(null);
                    requestBuilder = request.newBuilder().post(formBody);
                }
            } else {
                FormBody formBody = getFormBody(null);
                requestBuilder = request.newBuilder().post(formBody);
            }
        }

        return chain.proceed(requestBuilder.build());
    }

    /**
     * 获取公共参数
     *
     * @return
     */
    private Map addBasicParams() {
        Map paramsMap = new HashMap();
        paramsMap.put("login_time", "login_time");
        //设备类型Android 2
        paramsMap.put("deviceType", "android");
        //请求时间
        paramsMap.put("reqTime", String.valueOf(System.currentTimeMillis()));
        //系统版本号
        paramsMap.put("osSysVersion", AppUtils.getSysVersion());
        //设备唯一标识
        paramsMap.put("deviceId", DeviceUtil.getDeviceOnlyId(context));
        //网络类型 2G 3G 4G wifi
        paramsMap.put("netType", IntenetUtil.getNetworkState(context));
        //软件版本号 110
        String appVersion = AppUtils.getVersionName(context).replace(".", "");
        paramsMap.put("appVersion", appVersion);
        return paramsMap;
    }

    /**
     * GET 获取添加公共参数后的httpurl
     *
     * @param request
     * @return
     */
    private HttpUrl getHttpUrl(Request request) {
        HttpUrl httpUrl = request.url();
        int size = httpUrl.querySize();
        Map paramsMap = addBasicParams();
        //添加方法里面的参数
        for (int i = 0; i < size; i++) {
            paramsMap.put(httpUrl.queryParameterName(i), httpUrl.queryParameterValue(i));
        }
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        Iterator iterator = paramsMap.keySet().iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            httpUrlBuilder.setQueryParameter(String.valueOf(obj), String.valueOf(paramsMap.get(obj)));
        }
        return httpUrlBuilder.build();
    }

    /**
     * 获取FormBody
     *
     * @param queryMap
     * @return
     */
    private FormBody getFormBody(Map queryMap) {
        FormBody.Builder builder = new FormBody.Builder();
        Map paramsMap = addBasicParams();
        if (queryMap != null) {
            paramsMap.putAll(queryMap);
        }
        Iterator iterator = paramsMap.keySet().iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            builder.add(String.valueOf(obj), String.valueOf(paramsMap.get(obj)));
        }
        return builder.build();
    }

    /**
     * 将requestBody转换成string
     *
     * @param request
     * @return
     */
    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            buffer.clear();
            if (request != null) {
                request.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (IOException e) {
            return "did not work";
        }
    }
}
