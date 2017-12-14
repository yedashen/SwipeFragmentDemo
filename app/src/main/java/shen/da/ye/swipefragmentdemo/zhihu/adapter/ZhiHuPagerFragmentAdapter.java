package shen.da.ye.swipefragmentdemo.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import shen.da.ye.swipefragmentdemo.zhihu.ui.FirstPagerFragment;
import shen.da.ye.swipefragmentdemo.zhihu.ui.OtherPagerFragment;


/**
 * Created by YoKeyword on 16/6/5.
 *
 * @author YoKeyword
 */
public class ZhiHuPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public ZhiHuPagerFragmentAdapter(FragmentManager fm, String... titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return FirstPagerFragment.newInstance();
        } else {
            return OtherPagerFragment.newInstance(mTitles[position]);
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
