package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author ChenYe
 *         created by on 2017/12/12 0012. 15:50
 **/

public class ZhiHuFirstFragment extends SupportFragment {

    public static ZhiHuFirstFragment newInstance() {
        Bundle bundle = new Bundle();
        ZhiHuFirstFragment fragment = new ZhiHuFirstFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //当当前界面界面对用户可见的时候
    }
}
