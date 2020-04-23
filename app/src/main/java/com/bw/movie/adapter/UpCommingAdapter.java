package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.MovieInfoActivity;
import com.bw.movie.bean.UpcomingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UpCommingAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/192:07
 */
public class UpCommingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<UpcomingBean.ResultBean> list;
    ArrayList<UpcomingBean.ResultBean> beans = new ArrayList<>();

    public UpCommingAdapter(Context context, List<UpcomingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_upcomming_one, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        beans.clear();
        ((ViewHolder)holder).name.setText(list.get(0).getName());
        ((ViewHolder)holder).count.setText(list.get(0).getScore()+"分");
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        ((ViewHolder)holder).iv.setImageURI(uri);

        for (int i = 1; i < list.size(); i++){
            beans.add(list.get(i));
        }

        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        ((ViewHolder)holder).rv.setLayoutManager(manager);
        UpComAdapter adapter = new UpComAdapter(context, beans);
        ((ViewHolder)holder).rv.setAdapter(adapter);
        ((ViewHolder)holder).rv.addItemDecoration(new SpaceItemDecoration(10));

        ((ViewHolder)holder).iv.setOnClickListener(new View.OnClickListener() {
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
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;
        private final TextView name;
        private final TextView count;
        private final Button bu;
        private final RecyclerView rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            bu = itemView.findViewById(R.id.buy);
            rv = itemView.findViewById(R.id.rv);
        }
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
