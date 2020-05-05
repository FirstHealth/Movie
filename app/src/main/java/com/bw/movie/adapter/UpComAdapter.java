package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieInfoActivity;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.bean.upcoming.ResultBean_upcoming;
import com.bw.movie.utils.NetUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName HotAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/191:09
 */
public class UpComAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ResultBean_upcoming> list;

    public UpComAdapter(Context context, List<ResultBean_upcoming> list) {
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
        if (list.get(position).getWhetherReserve() == 1){
            ((ViewHolder) holder).bu.setText("已预约");
        }else if (list.get(position).getWhetherReserve() == 2){
            ((ViewHolder) holder).bu.setText("预约");
        }
        ((ViewHolder) holder).name.setText(list.get(position).getName());
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        ((ViewHolder) holder).iv.setImageURI(uri);
        //((ViewHolder)holder).count.setText(list.get(position).getWantSeeNum()+"人想看");
        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieInfoActivity.class);
                intent.putExtra("movieid",list.get(position).getMovieId()+"");
                context.startActivity(intent);
            }
        });
        int po = new Long(list.get(position).getMovieId()).intValue();
        ((ViewHolder)holder).bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetUtils.getInstance().getApis().doYuYue(po)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RegistBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RegistBean bean) {
                                ((ViewHolder)holder).bu.setText("已预约");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(context, "预约失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
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
