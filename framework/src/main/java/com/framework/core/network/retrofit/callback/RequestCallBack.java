package com.framework.core.network.retrofit.callback;

import android.content.Context;
import android.util.Log;

import com.framework.core.network.retrofit.HttpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by laulee on 2018/4/28.
 */

public abstract class RequestCallBack<T extends HttpResponse> implements Callback<T> {

    private Context context;

    public RequestCallBack(Context context) {
        this.context = context;
    }

    /**
     * 成功回调
     *
     * @param response
     */
    public abstract void onSuccess(T response);

    /**
     * 失败回调
     *
     * @param message
     */
    public abstract void onError(String message);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            Log.d("Response", response.body().toString());
            T result = response.body();
            if (result.isSuccess()) {
                onSuccess(result);
            } else {
                onApiException(result.code, result.getMessage());
            }
        }else {
            onFailure(call,new ApiException(response.code(),response.message()));
        }
    }

    /**
     * 接口约束的错误
     *
     * @param code
     * @param message
     */
    private void onApiException(int code, String message) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        ApiException apiException = ExceptionEngine.handleException(t);
        onError(apiException.getMessage());
    }
}
