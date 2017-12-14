package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.wechat.ui.delegate.CycleFragment;
import shen.da.ye.swipefragmentdemo.zhihu.ZhiHuActivity;
import shen.da.ye.swipefragmentdemo.zhihu.event.TabSelectedEvent;

/**
 * @author ChenYe
 *         created by on 2017/12/14 0014. 14:02
 **/

public class FirstPagerFragment extends SupportFragment {

    public static FirstPagerFragment newInstance() {
        Bundle bundle = new Bundle();
        FirstPagerFragment fragment = new FirstPagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhi_hu_first_pager, null);
        EventBusActivityScope.getDefault(_mActivity).register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.first_pager_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SupportFragment) getParentFragment()).start(CycleFragment.newInstance(0));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusActivityScope.getDefault(_mActivity).unregister(this);
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != ZhiHuActivity.SECOND) {
            return;
        }

        //在这里去获取父fragment(ViewPagerFragment)的viewpager目前的position是不是0，是就可以执行下面的针对
        //当前fragment的刷新或滚动需求了。其他跟当前fragment同级fragment也是这样
        int position = ((ViewPagerFragment) getParentFragment()).getCurrentPosition();
        if (position != 0) {
            return;
        }

        Toast.makeText(_mActivity, "可以执行你的代码块了", Toast.LENGTH_SHORT).show();
    }
}
