package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ReleaseingMovie;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName ReseasingAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/191:42
 */
public class ReseasingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ReleaseingMovie.ResultBean> list;

    public ReseasingAdapter(Context context, List<ReleaseingMovie.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_reseasing, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(list.get(position).getName());
        String date = new SimpleDateFormat("MM月dd日").format(
                new java.util.Date(list.get(position).getReleaseTime()));
        ((ViewHolder)holder).time.setText(date+"上映");
        ((ViewHolder)holder).count.setText(list.get(position).getWantSeeNum()+"人想看");
        Glide.with(context).load(list.get(position).getImageUrl()).into(((ViewHolder)holder).iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView name;
        private final TextView time;
        private final TextView count;
        private final Button bu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            count = itemView.findViewById(R.id.count);
            bu = itemView.findViewById(R.id.yue);
        }
    }
}
