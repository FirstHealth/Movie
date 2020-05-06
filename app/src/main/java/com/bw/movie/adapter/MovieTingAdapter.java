package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.SeatActivity;
import com.bw.movie.bean.MovieTingBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @ClassName MovieTingAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/5/623:05
 */
public class MovieTingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieTingBean.ResultBean> list;

    public MovieTingAdapter(Context context, List<MovieTingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_movieting, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(list.get(position).getScreeningHall());
        ((ViewHolder)holder).time.setText(list.get(position).getBeginTime()+"----"+list.get(position).getEndTime());
        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final LinearLayout ll;
        private final TextView time;
        private final TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
