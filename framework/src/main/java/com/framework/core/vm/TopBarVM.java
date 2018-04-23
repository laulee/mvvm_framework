package com.framework.core.vm;

import android.app.Activity;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.framework.core.R;
import com.framework.core.base.BaseVM;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/20.
 */

public class TopBarVM extends BaseVM {

    private Activity activity;
    public ObservableField<String> leftTxt = new ObservableField<>();
    public ObservableField<String> closeTxt = new ObservableField<>("关闭");
    public ObservableField<String> titleTxt = new ObservableField<>();
    public ObservableField<String> rightTxt = new ObservableField<>();
    public ObservableField<Drawable> leftImage = new ObservableField<>();
    public ObservableField<Drawable> rightImage = new ObservableField<>();
    public ObservableBoolean showClose = new ObservableBoolean(false);
    private View.OnClickListener leftListenter;
    private View.OnClickListener rightListenter;

    public TopBarVM(Activity activity) {
        this.activity = activity;
    }

    /**
     * 关闭网页按钮点击事件
     *
     * @param view
     */
    public void closeWvOnClick(View view) {
        activity.finish();
    }

    /**
     * 左边返回按钮点击事件
     *
     * @param view
     */
    public void leftLayoutOnClick(View view) {
        if (leftListenter != null) {
            leftListenter.onClick(view);
        } else {
            activity.finish();
        }
    }

    /**
     * 更多按钮点击事件
     *
     * @param view
     */
    public void rightLayoutOnClick(View view) {
        if (rightListenter != null) {
            rightListenter.onClick(view);
        }
    }

    /**
     * 设置左边返回文字
     *
     * @param leftTxtId
     * @param leftImageId
     */
    public void setLeftLayout(int leftTxtId, int leftImageId) {
        this.leftTxt.set(activity.getString(leftTxtId));
        Drawable drawable = ContextCompat.getDrawable(activity, leftImageId);
        this.leftImage.set(drawable);
    }

    /**
     * 设置右边更多文字
     *
     * @param rightTxtId
     * @param rightImageId
     */
    public void setRightLayout(int rightTxtId, int rightImageId) {
        this.leftTxt.set(activity.getApplicationContext().getString(rightTxtId));
        Drawable drawable = activity.getApplicationContext().getResources().getDrawable(rightImageId);
        this.leftImage.set(drawable);
    }

    /**
     * 设置topbar左边按钮事件
     *
     * @param leftListenter
     */
    public void setLeftListenter(View.OnClickListener leftListenter) {
        this.leftListenter = leftListenter;
    }

    /**
     * 设置标题
     *
     * @param titleTxt
     */
    public void setTitleTxt(String titleTxt) {
        this.titleTxt.set(titleTxt);
    }

    /**
     * 显示webview关闭按钮
     */
    public void setShowClose() {
        showClose.set(true);
    }
}
