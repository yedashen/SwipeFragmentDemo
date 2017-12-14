package shen.da.ye.swipefragmentdemo.zhihu.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shen.da.ye.swipefragmentdemo.R;
import shen.da.ye.swipefragmentdemo.zhihu.entity.Article;

/**
 * @author ChenYe
 *         created by on 2017/12/13 0013. 17:10
 **/

public class FirstHomeAdapter extends RecyclerView.Adapter<FirstHomeAdapter.HomeViewHolder> {

    private List<Article> mItems = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;
    private OnItemClickListener mListener = null;

    public FirstHomeAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_zhihu_home_first, null);
        final HomeViewHolder holder = new HomeViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClickListener(holder.getAdapterPosition(),holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        Article article = mItems.get(position);

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(holder.mIv, String.valueOf(position) + "_image");
        ViewCompat.setTransitionName(holder.mTv, String.valueOf(position) + "_tv");

        holder.mIv.setImageResource(article.getImgRes());
        holder.mTv.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mIv;
        public final TextView mTv;

        public HomeViewHolder(View itemView) {
            super(itemView);
            mIv = (ImageView) itemView.findViewById(R.id.img);
            mTv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public interface OnItemClickListener {

        /**
         * 点击事件
         *
         * @param position
         * @param holder
         */
        void onItemClickListener(int position, HomeViewHolder holder);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setData(List<Article> data) {
        mItems.clear();
        mItems.addAll(data);
        notifyDataSetChanged();
    }

    public Article getItem(int position) {
        return mItems.get(position);
    }
}
