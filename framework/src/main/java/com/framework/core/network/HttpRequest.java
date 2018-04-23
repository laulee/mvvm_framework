package com.framework.core.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.framework.core.utils.FastJsonUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class HttpRequest<T> extends Request<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private final ICallBack callBack;
    private Class<T> mClass;
    private Map<String, String> mapParams;

    /**
     * POST 请求
     *
     * @param url
     * @param mapParams
     * @param tClass
     * @param callBack
     */
    public HttpRequest(String url, Map<String, String> mapParams, Class<T> tClass, final ICallBack callBack) {
        super(Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error.getMessage());
            }
        });
        this.callBack = callBack;
        this.mClass = tClass;
        this.mapParams = mapParams;
    }

    /**
     * GET 请求
     *
     * @param url
     * @param tClass
     * @param callBack
     */
    public HttpRequest(String url, Class<T> tClass, final ICallBack callBack) {
        super(Method.GET, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error.getMessage());
            }
        });
        this.callBack = callBack;
        this.mClass = tClass;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            T result = FastJsonUtils.getBean(jsonString, mClass);
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (callBack != null) {
            callBack.onSuccess(response);
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mapParams != null)
            return mapParams;
        else {
            return super.getParams();
        }
    }

    public interface ICallBack<T> {

        void onSuccess(T response);

        void onError(String message);
    }
}
