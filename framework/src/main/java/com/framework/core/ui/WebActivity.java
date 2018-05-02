package com.framework.core.ui;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.framework.core.R;
import com.framework.core.base.BaseTopBarActivity;
import com.framework.core.databinding.ActivityWebBinding;
import com.framework.core.manager.WebViewManager;

import java.util.Map;

/**
 * Created by laulee on 2018/4/28.
 */

public class WebActivity extends BaseTopBarActivity<ActivityWebBinding> {
    @Override
    protected void setViewModel() {

    }

    @Override
    protected void initData() {
        String title = getIntent().getStringExtra("title");
        String loadUrl = getIntent().getStringExtra("loadUrl");
        baseTopBarVM.topBarVM.setTitleTxt(title);
        WebViewManager.initWeb(this, cvb.webview);
        cvb.webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(view.canGoBack()){
                    baseTopBarVM.topBarVM.setShowClose();
                }
            }
        });
        cvb.webview.loadUrl(loadUrl);
    }

    @Override
    protected void setTopBar() {
        super.setTopBar();
        baseTopBarVM.topBarVM.setLeftLayout(R.string.left_title, R.drawable.back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }
}
