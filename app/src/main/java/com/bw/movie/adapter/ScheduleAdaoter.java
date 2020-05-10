package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ScheduleBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleAdaoter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ScheduleBean.ResultBean> result;

    public ScheduleAdaoter(Context context, List<ScheduleBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.hotlistfragmnetadaoter, null);
        ViewHoder viewHoder = new ViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScheduleBean.ResultBean resultBean = result.get(position);
        Glide.with(context).load(resultBean.getImageUrl()).into(((ViewHoder)holder).iv);
        ((ViewHoder)holder).tv_name.setText(resultBean.getName());
        ((ViewHoder)holder).tv_dy.setText("导演:"+resultBean.getDirector());
//        String starring = resultBean.getStarring();
//        String substring = starring.substring(0, 10);
        ((ViewHoder)holder).tv_zy.setText("主演:"+resultBean.getStarring());
        ((ViewHoder)holder).tv_pf.setText("评分:"+resultBean.getScore());

        ((ViewHoder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monClick!=null){
                    monClick.onEner( new Long(result.get(position).getMovieId()).intValue());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public OnClick monClick;

    public void onclick(OnClick onClick){
        monClick=onClick;
    }

    public interface OnClick{
        void onEner(int id);
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_dy)
        TextView tv_dy;
        @BindView(R.id.tv_zy)
        TextView tv_zy;
        @BindView(R.id.tv_pf)
        TextView tv_pf;
        @BindView(R.id.bt_gp)
        Button bt_gp;
        @BindView(R.id.ll)
        LinearLayout ll;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
