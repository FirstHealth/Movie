package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.FindUserFollowCinemaListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CinemaFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<FindUserFollowCinemaListBean.ResultBean> result;

    public CinemaFragmentAdapter(Context context, List<FindUserFollowCinemaListBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.cinemafragmentadapter, null);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FindUserFollowCinemaListBean.ResultBean resultBean = result.get(position);
        Glide.with(context).load(resultBean.getLogo()).into(((ViewHoder)holder).iv);
        ((ViewHoder)holder).tv_name.setText(resultBean.getName());
        ((ViewHoder)holder).tv_dz.setText(resultBean.getAddress());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_dz)
        TextView tv_dz;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
