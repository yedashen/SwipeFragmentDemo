package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/11 0011. 17:01
 *         这个界面主要测试作用是：
 *         （1）如何跳转界面跳转界面；
 *         （2）怎么在跳转界面的时候传递数据；
 *         （3）如果关闭当前界面；
 *         （4）如何使用手动侧滑退出。
 *         <p>
 *         解决：
 *         （1）用start(fragment)方法来跳转界面，但是记住要分场景来由谁调用这个方法，记得看我MainFragmet的类
 *         的备注
 *         （2）直接跟原来的一样，fragment.setArguments(bundle);，然后在onCreate里面getArguments()；
 *         （3）_mActivity.onBackPressed();
 *         （4）要当前fragment继承SwipeBackFragment和在onCreateView的return哪里return attachToSwipeBack(view);
 **/

public class MessageDetailFragment extends SwipeBackFragment {


    private String mData;

    public static MessageDetailFragment newInstance(String toast) {
        Bundle bundle = new Bundle();
        bundle.putString("key", toast);
        MessageDetailFragment fragment = new MessageDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mData = bundle.getString("key");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_detail, null);
        initView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置滑动的时候的视觉差,默认是0.33f
        setParallaxOffset(0.33f);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        //尽量除了初始化控件信息意外其他的操作在入场动画结束以后再去执行，这样就基本不会造成动画卡顿
        Toast.makeText(getContext(), mData, Toast.LENGTH_SHORT).show();
    }

    private void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
