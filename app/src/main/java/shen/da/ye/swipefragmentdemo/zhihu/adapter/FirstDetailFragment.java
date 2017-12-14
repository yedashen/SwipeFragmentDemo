package shen.da.ye.swipefragmentdemo.zhihu.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.wechat.ui.delegate.CycleFragment;
import shen.da.ye.swipefragmentdemo.zhihu.entity.Article;

/**
 * @author ChenYe
 *         created by on 2017/12/13 0013. 17:42
 **/

public class FirstDetailFragment extends SupportFragment {

    private Article mArticle;
    private ImageView mIv;
    private TextView mTv;
    private Button mBt;

    public static FirstDetailFragment newInstance(Article article) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("key", article);
        FirstDetailFragment fragment = new FirstDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mArticle = (Article) bundle.getParcelable("key");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_first_detail, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mIv = (ImageView) view.findViewById(R.id.img_detail);
        mTv = (TextView) view.findViewById(R.id.tv_content);
        mBt = (Button) view.findViewById(R.id.detail_test);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mIv.setImageResource(mArticle.getImgRes());
        mTv.setText(mArticle.getTitle());
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(1));
            }
        });
    }
}
