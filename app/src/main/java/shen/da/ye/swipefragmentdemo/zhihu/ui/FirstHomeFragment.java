package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.zhihu.ZhiHuActivity;
import shen.da.ye.swipefragmentdemo.zhihu.adapter.FirstDetailFragment;
import shen.da.ye.swipefragmentdemo.zhihu.adapter.FirstHomeAdapter;
import shen.da.ye.swipefragmentdemo.zhihu.entity.Article;
import shen.da.ye.swipefragmentdemo.zhihu.event.TabSelectedEvent;
import shen.da.ye.swipefragmentdemo.zhihu.helper.DetailTransition;

/**
 * @author ChenYe
 *         created by on 2017/12/13 0013. 15:26
 **/

public class FirstHomeFragment extends SupportFragment {

    private SwipeRefreshLayout mRefresshLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private String[] mTitles = new String[]{
            "Use imagery to express a distinctive voice and exemplify creative excellence.",
            "An image that tells a story is infinitely more interesting and informative.",
            "The most powerful iconic images consist of a few meaningful elements, with minimal distractions.",
            "Properly contextualized concepts convey your message and brand more effectively.",
            "Have an iconic point of focus in your imagery. Focus ranges from a single entity to an overarching composition."
    };
    private int[] mImgRes = new int[]{
            R.mipmap.bg_first, R.mipmap.bg_second, R.mipmap.bg_third, R.mipmap.bg_fourth, R.mipmap.bg_fifth
    };

    public static FirstHomeFragment newStance() {
        Bundle bundle = new Bundle();
        FirstHomeFragment fragment = new FirstHomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_home, null);
        EventBusActivityScope.getDefault(_mActivity).register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRefresshLayout = (SwipeRefreshLayout) view.findViewById(R.id.home_sfl);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_rvc);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final FirstHomeAdapter adapter = new FirstHomeAdapter(_mActivity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FirstHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, FirstHomeAdapter.HomeViewHolder holder) {
                Article item = adapter.getItem(position);
                FirstDetailFragment fragment = FirstDetailFragment.newInstance(item);
                // 这里是使用SharedElement的用例
                // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    setExitTransition(new Fade());
                    fragment.setEnterTransition(new Fade());
                    fragment.setSharedElementReturnTransition(new DetailTransition());
                    fragment.setSharedElementEnterTransition(new DetailTransition());

                    // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
                    // 25.1.0+的support包，SharedElement正常
                    extraTransaction()
                            .addSharedElement(((FirstHomeAdapter.HomeViewHolder) holder).mIv, getString(R.string.image_transition))
                            .addSharedElement(((FirstHomeAdapter.HomeViewHolder) holder).mTv, "tv")
                            .start(fragment);
                } else {
                    start(fragment);
                }
            }
        });

        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int index = i % 5;
            Article article = new Article(mTitles[index], mImgRes[index]);
            articleList.add(article);
        }
        adapter.setData(articleList);
    }

    /**
     * 底部按钮的重复点击事件：已经就是如果底部按钮之前已经选择的是Tab1的话，用户又点击了Tab1
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != ZhiHuActivity.FIRST) {
            //这个FirstHomeFragment是ZhiHuFirstFragment的第一个孩子,只需要他来实现这个方法，并且只接受
            //当tab 的Position == 0 的时候才会需要处理这个时间
            return;
        }

        //这里可以实现很多功能，如果当前界面的列表不在顶部就滑到顶部，在顶部就刷新。
        scrollToTop();
    }

    private void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusActivityScope.getDefault(_mActivity).unregister(this);
    }
}
