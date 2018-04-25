package com.framework.core.viewbindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public final class ImageViewBindingAdapter {

    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String image) {
        Glide.with(imageView.getContext()).load(image).into(imageView);
    }
}
