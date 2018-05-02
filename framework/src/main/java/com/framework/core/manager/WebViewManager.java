package com.framework.core.manager;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.util.DisplayMetrics;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by laulee on 2018/4/28.
 */

public class WebViewManager {

    /**
     * 初始化webview
     *
     * @param webView
     * @Param context
     */
    public static void initWeb(Context context, WebView webView) {
        if (null == webView) {
            return;
        }
        WebSettings webSettings = webView.getSettings();
        //允许弹出网页对话框
        webView.setWebChromeClient(new WebChromeClient());
        //设置初始缩放比例
        // webView.setInitialScale(100);

        // 设置WebView的一些缩放功能点:
        WebSettings settings = webView.getSettings();

        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);//解决https加载http图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //设置 缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // 在同种分辨率的情况下,屏幕密度不一样的情况下,自动适配页面:
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int scale = dm.densityDpi;
        if (scale == 240) { //
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (scale == 160) {
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (scale == 120) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (scale == DisplayMetrics.DENSITY_XHIGH) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (scale == DisplayMetrics.DENSITY_TV) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }

        settings.setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.setWebChromeClient(new WebChromeClient());
        // 只在本应用中跳转不打开浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受证书
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }
}
