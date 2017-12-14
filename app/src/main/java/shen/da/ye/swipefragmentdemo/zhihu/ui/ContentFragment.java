package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.wechat.ui.delegate.CycleFragment;

/**
 * @author ChenYe
 *         created by on 2017/12/14 0014. 15:45
 **/

public class ContentFragment extends SupportFragment {

    private String mName;

    public static ContentFragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mName = bundle.getString("name");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView contentTv = (TextView) view.findViewById(R.id.tv_content);
        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SupportFragment) getParentFragment()).start(CycleFragment.newInstance(0));
            }
        });

        contentTv.setText(mName);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Override
    public boolean onBackPressedSupport() {
        // ContentFragment是ShopFragment的栈顶子Fragment,可以在此处理返回按键事件
        return super.onBackPressedSupport();
    }
}
