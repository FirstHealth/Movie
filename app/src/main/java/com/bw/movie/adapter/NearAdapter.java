package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.NearBean;
import com.bw.movie.bean.RecomeBean;

import java.util.List;

/**
 * @ClassName RecomeAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/5/522:05
 */
public class NearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<NearBean.ResultBean> list;

    public NearAdapter(Context context, List<NearBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_near, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).address.setText(list.get(position).getAddress());
        ((ViewHolder)holder).name.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getLogo()).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).km.setText(list.get(position).getDistance()/1000.0+"千米");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView name;
        private final TextView address;
        private final TextView km;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            km = itemView.findViewById(R.id.km);
        }
    }
}
