package com.laulee.mvvmframework.ui;

import com.framework.core.base.BaseFragment;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.databinding.FragmentMainMusicBinding;
import com.laulee.mvvmframework.vm.MusicVM;

/**
 * Created by laulee on 2018/5/2.
 */

public class MusicFragment extends BaseFragment<FragmentMainMusicBinding> {
    private MusicVM musicVM;

    @Override
    protected void setViewModel() {
        musicVM = new MusicVM(getActivity());
        cvb.setViewModel(musicVM);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_music;
    }

    @Override
    protected void initData() {
        musicVM.getMusic();
    }
}
