package com.bw.movie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.UserFollowMovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<UserFollowMovieBean.ResultBean> result;

    public MovieAdapter(Context context, List<UserFollowMovieBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.movieadapter, null);
        ViewHoder viewHoder = new ViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserFollowMovieBean.ResultBean resultBean = result.get(position);
        Log.i("MovieAdapter",resultBean.getImageUrl());
        Glide.with(context).load(resultBean.getImageUrl()).into(((ViewHoder)holder).iv);
        ((ViewHoder)holder).tv_name.setText(resultBean.getName());
        ((ViewHoder)holder).tv_dy.setText("导演:"+resultBean.getDirector());
        ((ViewHoder)holder).tv_zy.setText("主演:"+resultBean.getStarring());
        ((ViewHoder)holder).tv_pf.setText("评分:"+resultBean.getScore());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHoder extends  RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_dy)
        TextView tv_dy;
        @BindView(R.id.tv_zy)
        TextView tv_zy;
        @BindView(R.id.tv_pf)
        TextView tv_pf;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
