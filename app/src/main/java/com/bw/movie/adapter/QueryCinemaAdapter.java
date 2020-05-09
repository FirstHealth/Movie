package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.SeatActivity;
import com.bw.movie.bean.QueryCinemaBean;

import java.util.List;

/**
 * @ClassName RecomeAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/5/522:05
 */
public class QueryCinemaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<QueryCinemaBean.ResultBean> list;
    String id;

    public QueryCinemaAdapter(Context context, List<QueryCinemaBean.ResultBean> list,String id) {
        this.context = context;
        this.list = list;
        this.id = id;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recome, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).address.setText(list.get(position).getAddress());
        ((ViewHolder)holder).name.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getLogo()).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SeatActivity.class);
                int cinemaId = list.get(position).getCinemaId();
                intent.putExtra("id",cinemaId+"");
                intent.putExtra("ima",list.get(position).getLogo());
                intent.putExtra("movieid",id+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView name;
        private final TextView address;
        private final LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
