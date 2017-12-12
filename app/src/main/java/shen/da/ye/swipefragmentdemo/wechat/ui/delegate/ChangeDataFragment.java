package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/12 0012. 14:17
 **/

public class ChangeDataFragment extends SwipeBackFragment {

    private EditText mEt;
    private String mTitle;
    private Button mConfirmBt;
    private ImageView mBackIv;
    private static final String TITLE = "title";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change, null);
        initView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mEt.setText(mTitle);
        mConfirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(TestFragment.KEY, mEt.getText().toString());
                setFragmentResult(RESULT_OK, bundle);
                _mActivity.onBackPressed();
            }
        });

        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    private void initView(View view) {
        mEt = (EditText) view.findViewById(R.id.data_et);
        mConfirmBt = view.findViewById(R.id.bt_confirm);
        mBackIv = (ImageView) view.findViewById(R.id.back_iv);
    }

    public static ChangeDataFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        ChangeDataFragment fragment = new ChangeDataFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
