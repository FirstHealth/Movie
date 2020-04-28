package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.MovieCinecisnBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName MoviePingAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2621:34
 */
public class MoviePingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieCinecisnBean.ResultBean> list;

    public MoviePingAdapter(Context context, List<MovieCinecisnBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_movieping, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(list.get(position).getCommentUserName());
        Glide.with(context).load(list.get(position).getCommentHeadPic()).apply(new RequestOptions().circleCrop()).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).math.setText(list.get(position).getGreatNum()+"人");
        long commentTime = list.get(position).getCommentTime();
        String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(
                new java.util.Date(commentTime));
        ((ViewHolder)holder).time.setText(date);
        ((ViewHolder)holder).xx.setText(list.get(position).getCommentContent());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView name;
        private final TextView time;
        private final TextView xx;
        private final TextView math;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.head);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            xx = itemView.findViewById(R.id.xx);
            math = itemView.findViewById(R.id.math);
        }
    }
}
