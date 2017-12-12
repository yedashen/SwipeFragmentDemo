package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/12 0012. 14:51
 **/

public class TestFragment extends SwipeBackFragment {

    private TextView mTv;
    static final String KEY = "key";
    private String mTestData;

    private static final int REQ_MODIFY_FRAGMENT = 100;
    private String mTitle = "暂无数据";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTestData = bundle.getString(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        mTv = (TextView) view.findViewById(R.id.test_tv);
        view.findViewById(R.id.go_change_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForResult(ChangeDataFragment.newInstance(mTv.getText().toString()),
                        REQ_MODIFY_FRAGMENT);
            }
        });
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mTv.setText(mTestData);
    }

    public static TestFragment newInstance() {
        Bundle bundle = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            mTv.setText(data.getString(KEY));
            getArguments().putString(KEY, data.getString(KEY));
        }
    }
}
