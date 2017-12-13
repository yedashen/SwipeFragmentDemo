package shen.da.ye.swipefragmentdemo.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.view.BottomBar;
import shen.da.ye.swipefragmentdemo.view.BottomBarTab;
import shen.da.ye.swipefragmentdemo.zhihu.ui.ZhiHuFirstFragment;
import shen.da.ye.swipefragmentdemo.zhihu.ui.ZhiHuFourthFragment;
import shen.da.ye.swipefragmentdemo.zhihu.ui.ZhiHuSecFragment;
import shen.da.ye.swipefragmentdemo.zhihu.ui.ZhiHuThirdFragment;

/**
 * @author ChenYe
 *         created by on 2017/12/12 0012. 15:17
 **/

public class ZhiHuActivity extends SupportActivity implements BaseMainFragment.OnBackToFirstListener {

    SupportFragment[] mFragments = new SupportFragment[4];
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FOURTH = 3;
    private BottomBar mBottomBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu);
        ZhiHuFirstFragment firstFragment = findFragment(ZhiHuFirstFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = ZhiHuFirstFragment.newInstance();
            mFragments[SECOND] = ZhiHuSecFragment.newInstance();
            mFragments[THIRD] = ZhiHuThirdFragment.newInstance();
            mFragments[FOURTH] = ZhiHuFourthFragment.newInstance();

            loadMultipleRootFragment(R.id.zhi_hu_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(ZhiHuSecFragment.class);
            mFragments[THIRD] = findFragment(ZhiHuThirdFragment.class);
            mFragments[FOURTH] = findFragment(ZhiHuFourthFragment.class);
        }

        initView();
    }

    private void initView() {
        mBottomBar = (BottomBar) findViewById(R.id.zhi_hu_br);
        mBottomBar
                .addItemTab(new BottomBarTab(this, R.mipmap.ic_home_white_24dp))
                .addItemTab(new BottomBarTab(this, R.mipmap.ic_discover_white_24dp))
                .addItemTab(new BottomBarTab(this, R.mipmap.ic_message_white_24dp))
                .addItemTab(new BottomBarTab(this, R.mipmap.ic_account_circle_white_24dp));
        mBottomBar.setTabSeletcListener(new BottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }

    /**
     * 这里暂没实现,忽略,这个就是像京东的那个一样，跳转界面之后让底部按钮消失，这样能让跳转之后的界面看起来更大
     * 一些，然后回到主界面的时候让底部按钮又出现
     */
//    @Subscribe
//    public void onHiddenBottombarEvent(boolean hidden) {
//        if (hidden) {
//            mBottomBar.hide();
//        } else {
//            mBottomBar.show();
//        }
//    }
}
