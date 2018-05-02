package com.laulee.mvvmframework.viewbinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.entity.ZhihuThemeEntity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by laulee on 2018/5/2.
 */

public final class BannerViewBindingAdapter {

    @BindingAdapter(value = {"bannerListBeanX", "defaultImage"}, requireAll = false)
    public static void setBannerx(final Banner banner, ObservableArrayList<ZhihuThemeEntity.StoriesBean> list, Drawable defaultImage) {
        if (null == defaultImage) {
            defaultImage = ContextCompat.getDrawable(banner.getContext(), R.drawable.ic_home_white_24dp);
        }
        if (list != null && list.size() > 0) {
            banner.setImages(list);
        }
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ZhihuThemeEntity.StoriesBean storiesBean = (ZhihuThemeEntity.StoriesBean) path;
                Glide.with(context).load(storiesBean.getImages().get(0)).into(imageView);
            }
        }).start();
    }


    /**
     * 配置banner高度
     *
     * @param banner
     * @param height
     */
    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(Banner banner, float height) {
        ViewGroup.LayoutParams params = banner.getLayoutParams();
        params.height = (int) height;
        banner.setLayoutParams(params);
    }

    @BindingAdapter(value = {"bannerStyle", "bannerGravity"}, requireAll = false)
    public static void setBannerStyle(final Banner banner, int bannerStyle, int gravity) {
        if (bannerStyle == 0) {
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        } else {
            banner.setBannerStyle(bannerStyle);
        }

        banner.setIndicatorGravity(gravity);
    }
}
