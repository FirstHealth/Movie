package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieInfoActivity;
import com.bw.movie.bean.hot.ResultBean_hot;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @ClassName HotAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/191:09
 */
public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ResultBean_hot> list;

    public HotAdapter(Context context, List<ResultBean_hot> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_hot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).name.setText(list.get(position).getName());
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView name;
        private final Button bu;
        private final LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.tv);
            bu = itemView.findViewById(R.id.buy);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
