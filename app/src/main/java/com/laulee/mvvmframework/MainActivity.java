package com.laulee.mvvmframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.framework.core.base.BaseActivity;
import com.framework.core.base.BaseTopBarActivity;
import com.laulee.mvvmframework.databinding.ActivityMainBinding;

public class MainActivity extends BaseTopBarActivity<ActivityMainBinding> {

    @Override
    protected void setViewModel() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setTopBar() {
        super.setTopBar();
        baseTopBarVM.topBarVM.setLeftLayout(R.string.top_left,R.drawable.back);
        baseTopBarVM.topBarVM.setLeftListenter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "点击左边", Toast.LENGTH_LONG).show();
            }
        });
        baseTopBarVM.topBarVM.setShowClose();
    }
}