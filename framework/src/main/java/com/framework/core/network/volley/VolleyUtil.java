package com.framework.core.network.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class VolleyUtil {

    public static final String TAG = VolleyUtil.class.getName();

    private static RequestQueue mRequestQueue;

    /**
     * 初始化
     *
     * @param context
     */
    public static void initConfig(Context context) {
        if (mRequestQueue == null) {
            synchronized (VolleyUtil.class) {
                if (mRequestQueue == null) {
                    mRequestQueue = Volley.newRequestQueue(context);
                }
            }
        }
    }

    /**
     * 添加请求方法
     *
     * @param request
     */
    public static void addRequest(Request request) {
        mRequestQueue.add(request);
    }

    /**
     * 获取请求队列
     *
     * @return
     */
    public static RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            throw new RuntimeException("请先初始化RequestQueue");
        return mRequestQueue;
    }

    /**
     * 取消
     */
    public void cancel() {
        mRequestQueue.cancelAll(TAG);
    }
}
