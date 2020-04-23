package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.MovieInfoActivity;
import com.bw.movie.bean.ReleaseingMovie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName ReseasingAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/191:42
 */
public class Reseasing2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ReleaseingMovie.ResultBean> list;

    public Reseasing2Adapter(Context context, List<ReleaseingMovie.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_reseasing2, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(list.get(position).getName());
        if (list.get(position).getDirector() != null) {
            ((ViewHolder) holder).time.setText("导演：" + list.get(position).getDirector());
        }else {
            ((ViewHolder) holder).time.setText("导演：王晶");
        }
        ((ViewHolder)holder).count.setText("评分："+list.get(position).getScore());
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        ((ViewHolder)holder).iv.setImageURI(uri);

        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieInfoActivity.class);
                intent.putExtra("movieid",list.get(position).getMovieId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;
        private final TextView name;
        private final TextView time;
        private final TextView count;
        private final Button bu;
        private final LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            count = itemView.findViewById(R.id.count);
            bu = itemView.findViewById(R.id.yue);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
