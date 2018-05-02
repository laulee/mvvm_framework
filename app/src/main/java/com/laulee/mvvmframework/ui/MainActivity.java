package com.laulee.mvvmframework.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.framework.core.base.BaseTopBarActivity;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.databinding.ActivityMainBinding;
import com.laulee.mvvmframework.vm.MainVM;

public class MainActivity extends BaseTopBarActivity<ActivityMainBinding> implements BottomNavigationBar.OnTabSelectedListener {

    private MainVM mainVM;
    private IndexFragment indexFragment;
    private MusicFragment musicFragmnet;

    @Override
    protected void setViewModel() {
        mainVM = new MainVM();
        cvb.setViewModel(mainVM);
        cvb.bottomNavigationBarContainer.setTabSelectedListener(this);
        cvb.bottomNavigationBarContainer.setMode(BottomNavigationBar.MODE_FIXED);
        cvb.bottomNavigationBarContainer.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        cvb.bottomNavigationBarContainer.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "音乐").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "电影").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "游戏").setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成
    }

    @Override
    protected void initData() {
        indexFragment = new IndexFragment();
        showFragment(indexFragment);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setTopBar() {
        super.setTopBar();
        baseTopBarVM.topBarVM.setTitleTxt("首页");
    }

    /**
     * 切换fragment
     *
     * @param fragment
     */
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

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                showFragment(indexFragment);
                break;
            case 1:
                if (musicFragmnet == null) {
                    musicFragmnet = new MusicFragment();
                }
                showFragment(musicFragmnet);
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}