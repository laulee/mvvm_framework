package com.laulee.mvvmframework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.laulee.mvvmframework.R;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/25.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        showFragment(new IndexFragment());
    }


    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentByTag == null) {
            transaction.add(R.id.main_content, fragment, fragment.getClass().getName());
        }
        transaction.show(fragment);
        transaction.commit();
    }
}
