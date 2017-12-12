package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class CycleFragment extends SwipeBackFragment {
    private static final String ARG_NUMBER = "arg_number";

    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;

    private int mNumber;

    public static CycleFragment newInstance(int number) {
        CycleFragment fragment = new CycleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNumber = args.getInt(ARG_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cycle, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);
        mBtnNextWithFinish = (Button) view.findViewById(R.id.btn_next_with_finish);

        String title = "CyclerFragment " + mNumber;


        mTvName.setText(title + "\n" + getString(R.string.can_swipe));
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(mNumber + 1));
            }
        });
        mBtnNextWithFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWithPop(CycleFragment.newInstance(mNumber + 1));
            }
        });

        view.findViewById(R.id.back_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        TextView titleTv = (TextView) view.findViewById(R.id.title_tv);
        titleTv.setText(title);
    }
}