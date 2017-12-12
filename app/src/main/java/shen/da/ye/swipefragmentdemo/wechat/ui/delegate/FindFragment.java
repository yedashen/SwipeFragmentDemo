package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/11 0011. 15:13
 *         （1）懒加载：这个当在MainFragment将这个fragment new出来的时候就已经走了很多当前fragment的生命周期
 *         了，比如你打log就知道，在点击最开始界面的“去仿微信交互界面按钮”就会打印onCreateView里面的initView()
 *         日志了，当点击发现切换到当前界面的时候才会打印onLazyInitView里面的log
 *         （2）下面的两个点击事件都是在启动新的fragment的时候自己设置动画的，一个需要隐藏当前的fragment一个
 *         不需要,请看下面的备注
 **/

public class FindFragment extends SupportFragment {

    private static String TAG = FindFragment.class.getSimpleName();
    private Button mStartNewBt;
    private Button mShowDialogBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Log.e(TAG, "initView()");
        mStartNewBt = (Button) view.findViewById(R.id.bt_start_new);
        mShowDialogBt = (Button) view.findViewById(R.id.bt_show_dialog);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.e(TAG, "onLazyInitView()");
        mStartNewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏
                ((MainFragment) getParentFragment()).startBrotherFragment(
                        CycleFragment.newInstance(0)
                        , R.anim.v_fragment_enter, R.anim.v_fragment_pop_exit,
                        R.anim.v_fragment_pop_enter, R.anim.v_fragment_exit
                        , true);
            }
        });

        mShowDialogBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不隐藏
                ((MainFragment) getParentFragment()).startBrotherFragment(
                        ViewFragment.newInstance()
                        , R.anim.v_fragment_enter, 0, 0, R.anim.v_fragment_exit
                        , false);
            }
        });
    }

    public static FindFragment newInstance() {
        Bundle bundle = new Bundle();
        FindFragment fragment = new FindFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        Log.e(TAG, "FindFragment目前可见");
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        Log.e(TAG, "FindFragment目前不可见");
    }
}
