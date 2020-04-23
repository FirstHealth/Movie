package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieDataBean;

import java.util.List;

/**
 * @ClassName VideoAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/240:52
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieDataBean.ResultBean.ShortFilmListBean> list;

    public VideoAdapter(Context context, List<MovieDataBean.ResultBean.ShortFilmListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_video, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getVideoUrl());
        ((ViewHolder)holder).vv.setVideoURI(uri);
        ((ViewHolder)holder).tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewHolder)holder).vv.start();
                ((ViewHolder)holder).tv.setVisibility(8);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final VideoView vv;
        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vv = itemView.findViewById(R.id.vv);
            tv = itemView.findViewById(R.id.bu);
        }
    }
}
