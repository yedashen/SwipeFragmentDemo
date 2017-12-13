package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.zhihu.BaseMainFragment;

/**
 * @author ChenYe
 *         created by on 2017/12/12 0012. 15:50
 **/

public class ZhiHuThirdFragment extends BaseMainFragment {

    public static ZhiHuThirdFragment newInstance() {
        Bundle bundle = new Bundle();
        ZhiHuThirdFragment fragment = new ZhiHuThirdFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhi_hu_third, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

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
