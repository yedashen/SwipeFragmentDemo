package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/11 0011. 15:13
 *         (1)跳转后的fragment像跳转前的fragment传递数据
 **/

public class MoreFragment extends SupportFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.bt_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).start(TestFragment.newInstance());
            }
        });
    }

    public static MoreFragment newInstance() {
        Bundle bundle = new Bundle();
        MoreFragment fragment = new MoreFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
