package shen.da.ye.swipefragmentdemo.zhihu;

import android.content.Context;

import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.zhihu.ui.ZhiHuFirstFragment;

/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 *
 * @author YoKey
 */
public abstract class BaseMainFragment extends SupportFragment {
    protected OnBackToFirstListener _mBackToFirstListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof ZhiHuFirstFragment) {
                // 如果是 第一个Fragment 则退出app
                _mActivity.finish();
            } else {
                // 如果不是,则回到第一个Fragment
                _mBackToFirstListener.onBackToFirstFragment();
            }
        }
        return true;
    }

    public interface OnBackToFirstListener {

        /**
         * 点击返回的时候回到首页第一个fragment
         */
        void onBackToFirstFragment();
    }
}
