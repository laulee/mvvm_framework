package com.framework.core.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framework.core.manager.AppManager;
import com.framework.core.manager.HomeWatchManager;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/20.
 */

public abstract class BaseActivity<CVB extends ViewDataBinding> extends AppCompatActivity {

    protected CVB cvb;
    protected int time;// 灭屏时间

    protected Timer timer;// 倒计时定时器，超过5分钟计算重新登录，不超过，输完手势密码直接进入退出时的页面
    private HomeWatchManager mHomeWatcher;//广播监听器
    private boolean DownHomeKey;

    /**
     * 设置viewModel
     */
    protected abstract void setViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        time = 60;
        super.onCreate(savedInstanceState);
        bindView();
        AppManager.getAppManager().addActivity(this);
        //是否注册eventBus事件
        if (enableEventBus()) {
            EventBus.getDefault().register(this);
        }
        initData();
    }

    /**
     * 初始化获取数据
     */
    protected abstract void initData();

    /**
     * 初始化databing绑定布局
     */
    protected void bindView() {
        if (getLayoutId() > 0) {
            cvb = DataBindingUtil.setContentView(this, getLayoutId());
            setViewModel();
        } else {
            throw new IllegalArgumentException("layout is not a inflate");
        }
    }

    /**
     * 是否注册事件
     *
     * @return
     */
    protected boolean enableEventBus() {
        return false;
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    public abstract int getLayoutId();

    @Override
    protected void onResume() {
        super.onResume();
        registerHomeListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHomeWatcher != null) {
            mHomeWatcher.stopWatch();
        }
        Activity activity = AppManager.getAppManager().currentActivity();
        if (activity == null) {
            DownHomeKey = false;
        }
    }

    @Override
    protected void onDestroy() {
        //移除栈中的activity
        AppManager.getAppManager().removeActivity(this);
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        if (enableEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 注册Home键的监听
     */
    protected void registerHomeListener() {
        mHomeWatcher = new HomeWatchManager(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatchManager.OnHomePressedListener() {

            @Override
            public void onHomePressed() {
                //TODO 进行点击Home键的处理
                DownHomeKey = true;
                timerManager(new TimerListener() {
                    @Override
                    public void listener() {
                        --time;
                        if (time == 0) {
                            timer.cancel();
                        }
                    }
                });
            }

            @Override
            public void onHomeLongPressed() {
                //TODO 进行长按Home键的处理
            }
        });
        mHomeWatcher.startWatch();//注册广播
    }

    public interface TimerListener {
        public void listener();
    }

    /**
     * 倒计时定时器，灭屏超过5分钟重新登录
     */
    protected void timerManager(final TimerListener timerListener) {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                timerListener.listener();
            }
        }, 1000, 1000);
    }
}