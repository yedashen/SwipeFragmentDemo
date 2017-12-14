package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.zhihu.adapter.MenuAdapter;

/**
 * @author ChenYe
 *         created by on 2017/12/14 0014. 15:40
 **/

public class MenuListFragment extends SupportFragment {

    private ArrayList<String> mNames;
    private RecyclerView mRecyclerView;
    private int mCurrentPosition = -1;
    private MenuAdapter mAdapter;
    private static final String SAVE_STATE_POSITION = "save_state_position";

    public static MenuListFragment newInstance(ArrayList<String> names) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("names", names);
        MenuListFragment fragment = new MenuListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNames = getArguments().getStringArrayList("names");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, null);
        initView(view);
        return view;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.menu_rcv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new MenuAdapter(_mActivity);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setDatas(mNames);
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showContent(position);
            }
        });

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            mAdapter.setItemChecked(mCurrentPosition);
        } else {
            mCurrentPosition = 0;
            mAdapter.setItemChecked(0);
        }
    }

    private void showContent(int position) {
        if (position == mCurrentPosition) {
            return;
        }
        mCurrentPosition = position;
        mAdapter.setItemChecked(position);
        ContentFragment fragment = ContentFragment.newInstance(mNames.get(position));
        ((ShopFragment) getParentFragment()).switchContentFragment(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_POSITION, mCurrentPosition);
    }
}
