package shen.da.ye.swipefragmentdemo.wechat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.wechat.ui.delegate.MainFragment;

/**
 * @author ChenYe
 *         created by on 2017/12/11 0011. 10:56
 **/

public class WeChatHomeActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_home);
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.wechat_container, MainFragment.newInstance());
        }
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
