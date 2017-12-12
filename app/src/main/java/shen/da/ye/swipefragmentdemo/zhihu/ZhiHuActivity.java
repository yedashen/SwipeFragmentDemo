package shen.da.ye.swipefragmentdemo.zhihu;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author ChenYe
 *         created by on 2017/12/12 0012. 15:17
 *         记得测试一下懒加载和见面重新可见的已经在ViewPager里面重新变成selected那个的时候，是不是有什么方法
 *         可以通知到界面上的，因为有时候有需求是要每次可见的时候都要刷新
 **/

public class ZhiHuActivity extends SupportActivity {

    SupportFragment[] mFragments = new SupportFragment[4];
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FOURTH = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}
