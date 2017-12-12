package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/11 0011. 11:15
 *         MessageFragment、FindFragment、MoreFragment是三个同等级的Fragment，并且是用的是同一个容器，
 *         不能直接在这三个Fragment里面调用start来跳转界面，要在这三个Fragment的父容器Fragment里面调用start,
 *         由MianFragment来执行跳转才能达到"SwipeBack"的效果。
 **/

public class MainFragment extends SupportFragment {

    private RadioGroup mRadioGroup;
    private static final String TAG = MainFragment.class.getSimpleName();
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private SupportFragment[] mFragments = new SupportFragment[3];
    private int prePosition = 0;
    private static final int REQ_MSG = 10;

    public static MainFragment newInstance() {
        Bundle bundle = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat_main, null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MessageFragment messageFragment = findChildFragment(MessageFragment.class);
        if (messageFragment == null) {
            Log.e(TAG, "onActivityCreated()___messageFragment == null");
            mFragments[FIRST] = MessageFragment.newInstance();
            mFragments[SECOND] = FindFragment.newInstance();
            mFragments[THIRD] = MoreFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST], mFragments[SECOND], mFragments[THIRD]);
        } else {
            Log.e(TAG, "onActivityCreated()___messageFragment != null");
            mFragments[FIRST] = messageFragment;
            mFragments[SECOND] = findChildFragment(FindFragment.class);
            mFragments[THIRD] = findChildFragment(MoreFragment.class);
        }
    }

    private void initView(View view) {
        mRadioGroup = (RadioGroup) view.findViewById(R.id.bottom_rg);
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int i = group.indexOfChild(group.findViewById(checkedId));
                showHideFragment(mFragments[i], mFragments[prePosition]);
                prePosition = i;
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MSG && resultCode == RESULT_OK) {

        }
    }

    /**
     * 至于为什么要通过父Fragment来跳转界面，你可以看我对这个MainFragment类的备注
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }


    /**
     * @param targetFragment
     * @param targetEnter
     * @param currentExit
     * @param currentEnter
     * @param target
     * @param isHide         true的时候是需要隐藏当前界面的
     */
    public void startBrotherFragment(SupportFragment targetFragment, int targetEnter, int currentExit,
                                     int currentEnter, int target, boolean isHide) {
        ExtraTransaction extraTransaction = extraTransaction()
//                        .setTag("CustomTag")
//                        . ...
                .setCustomAnimations(targetEnter, currentExit, currentEnter, target);
        if (isHide) {
            extraTransaction.start(targetFragment);
        } else {
            extraTransaction.startDontHideSelf(targetFragment);
        }
    }
}
