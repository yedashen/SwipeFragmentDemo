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

public class ZhiHuSecFragment extends BaseMainFragment {

    public static ZhiHuSecFragment newInstance() {
        Bundle bundle = new Bundle();
        ZhiHuSecFragment fragment = new ZhiHuSecFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhi_hu_sec, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(ViewPagerFragment.class) == null){
            loadRootFragment(R.id.zhi_hu_sec_container,ViewPagerFragment.newInstance());
        }
    }
}
