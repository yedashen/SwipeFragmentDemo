package shen.da.ye.swipefragmentdemo.wechat.ui.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/11 0011. 15:13
 **/

public class MessageFragment extends SupportFragment {

    private static final String TAG = MessageFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.msg_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(MessageDetailFragment.newInstance("上一个界面传递过来的吐司提示"));
//                start(MessageDetailFragment.newInstance("上一个界面传递过来的吐司提示"));//这个不适合当前场景使用，应该用父容器的来调通跳转
            }
        });
    }

    public static MessageFragment newInstance() {
        Bundle bundle = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        Log.e(TAG, "MessageFragment目前可见");
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        Log.e(TAG, "MessageFragment目前不可见");
    }
}
