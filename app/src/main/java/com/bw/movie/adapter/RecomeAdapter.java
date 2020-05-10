package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.RecomeBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @ClassName RecomeAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/5/522:05
 */
public class RecomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<RecomeBean.ResultBean> list;

    public RecomeAdapter(Context context, List<RecomeBean.ResultBean> list) {
        this.context = context;
        this.list = list;
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
        RecomeBean.ResultBean resultBean = list.get(position);
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
