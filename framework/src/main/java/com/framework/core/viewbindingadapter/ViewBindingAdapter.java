package com.framework.core.viewbindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.framework.core.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/20.
 */

public final class ViewBindingAdapter {
    /**
     * 设置view是否显示
     *
     * @param visible true - 显示
     * false - 不显示
     */
    @BindingAdapter({"visibility"})
    public static void viewVisibility(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
