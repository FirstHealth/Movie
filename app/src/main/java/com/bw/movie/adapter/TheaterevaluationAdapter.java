package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.FindAllCinemaCommentBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TheaterevaluationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<FindAllCinemaCommentBean.ResultBean> result;

    public TheaterevaluationAdapter(Context context, List<FindAllCinemaCommentBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.theaterevaluationadapter, null);
        ViewHoder viewHoder = new ViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FindAllCinemaCommentBean.ResultBean resultBean = result.get(position);
        Glide.with(context).load(resultBean.getCommentHeadPic()).into(((ViewHoder)holder).iv);
        ((ViewHoder)holder).tv_name.setText(resultBean.getCommentUserName());
        ((ViewHoder)holder).tv_pl.setText(resultBean.getCommentContent());
        long commentTime = resultBean.getCommentTime();
        Date date = new Date(commentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        ((ViewHoder)holder).tv_sj.setText(format);
        ((ViewHoder)holder).iv_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewHoder)holder).iv_zan.setImageResource(R.mipmap.zan1);
                resultBean.setIsGreat(1);
                int greatNum = resultBean.getGreatNum();
                resultBean.setGreatNum(greatNum+1);
                if(monClick!=null){
                    monClick.setint(resultBean.getCommentId());
                }
            }
        });
    }

    public OnClick monClick;

    public void setOnClick(OnClick onClick){
        monClick=onClick;
    }

    public interface OnClick{
        void setint(int id);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_sj)
        TextView tv_sj;
        @BindView(R.id.tv_pl)
        TextView tv_pl;
        @BindView(R.id.iv_zan)
        ImageView iv_zan;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
