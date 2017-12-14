package shen.da.ye.swipefragmentdemo.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;
import shen.da.ye.swipefragmentdemo.R;

/**
 * @author ChenYe
 *         created by on 2017/12/14 0014. 14:49
 **/

public class ShopFragment extends SupportFragment {

    public static ShopFragment newInstance() {
        Bundle bundle = new Bundle();
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayList<String> names = generateData();
        if (findChildFragment(MenuListFragment.class) == null) {
            loadRootFragment(R.id.shop_container_1, MenuListFragment.newInstance(names));
            loadRootFragment(R.id.shop_container_2, ContentFragment.newInstance(names.get(0)));
        }
    }

    private ArrayList<String> generateData() {
        ArrayList<String> names = new ArrayList<>();
        names.add("销量排行");
        names.add("炒菜");
        names.add("汤面类");
        names.add("煲汤");
        names.add("汤");
        names.add("小菜");
        names.add("酒水饮料");
        names.add("盖浇饭类");
        names.add("炒面类");
        names.add("拉面类");
        names.add("盖浇面类");
        names.add("特色菜");
        names.add("加料");
        names.add("馄饨类");
        names.add("其他");
        return names;
    }

    /**
     * 重写返回按钮
     * ContentFragment是ShopFragment的栈顶子Fragment,会先调用ContentFragment的onBackPressedSupport方法
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        return false;
//        return super.onBackPressedSupport();
    }

    /**
     * 替换加载 内容Fragment
     *
     * @param fragment
     */
    public void switchContentFragment(ContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(ContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }
    }
}
