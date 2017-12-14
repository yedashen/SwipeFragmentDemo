package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.zhihu.BaseMainFragment;

/**
 * @author ChenYe
 *         created by on 2017/12/12 0012. 15:50
 *         建议第一个fragment不要在动画关闭之后在调用加载fragment，因为会造成有一段间隙是空白界面停留。
 **/

public class ZhiHuFirstFragment extends BaseMainFragment {

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
        View view = inflater.inflate(R.layout.fragment_zhi_hu_first, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (findChildFragment(FirstHomeFragment.class) == null) {
            loadRootFragment(R.id.zhi_hu_first_container, FirstHomeFragment.newStance());
        }
    }
}
