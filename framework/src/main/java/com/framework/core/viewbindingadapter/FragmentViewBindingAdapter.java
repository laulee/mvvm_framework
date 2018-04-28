package com.framework.core.viewbindingadapter;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public final class FragmentViewBindingAdapter {

    @BindingAdapter("notifyFragment")
    public static void modifyFragment(FrameLayout frameLayout, Fragment fragment) {
        FragmentManager fragmentManager = ((AppCompatActivity) frameLayout.getContext()).getSupportFragmentManager();
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentByTag == null) {
            transaction.add(frameLayout.getId(), fragment, fragment.getClass().getName());
        }
        transaction.show(fragment);
        transaction.commit();
    }
}
