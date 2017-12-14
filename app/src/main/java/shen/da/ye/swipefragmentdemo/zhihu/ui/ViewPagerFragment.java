package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.zhihu.adapter.ZhiHuPagerFragmentAdapter;

/**
 * @author ChenYe
 *         created by on 2017/12/14 0014. 10:45
 **/

public class ViewPagerFragment extends SupportFragment {

    private ViewPager mViewPager;

    public static ViewPagerFragment newInstance() {
        Bundle bundle = new Bundle();
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_second_pager, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        mViewPager.setAdapter(new ZhiHuPagerFragmentAdapter(getChildFragmentManager(), "推荐"
                , "热门", "收藏", "更多"));
        tabLayout.setupWithViewPager(mViewPager);
    }

    public int getCurrentPosition() {
        return mViewPager.getCurrentItem();
    }
}
