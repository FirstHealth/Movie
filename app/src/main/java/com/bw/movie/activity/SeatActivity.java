package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.MovieTingAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieTingBean;
import com.bw.movie.bean.QueryZuoWeiBean;
import com.bw.movie.bean.SiteBean;
import com.bw.movie.custom.SeatTable;
import com.bw.movie.custom.SelectSiteView;
import com.bw.movie.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SeatActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.sele)
    SelectSiteView sele;
    @BindView(R.id.count)
    TextView tv;
    @BindView(R.id.rv)
    RecyclerView rv;
    private String id;
    ArrayList<SiteBean> list = new ArrayList<>();
    private String ima;
    private String movieid;
    private int hallId;

    @Override
    protected int getReasuce() {
        return R.layout.activity_seat;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        ima = intent.getStringExtra("ima");
        movieid = intent.getStringExtra("movieid");
        Glide.with(this).load(ima).into(iv);
    }

    @Override
    protected void getData() {
        NetUtils.getInstance().getApis().doTing(Integer.valueOf(movieid),Integer.valueOf(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieTingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieTingBean bean) {
                        List<MovieTingBean.ResultBean> result = bean.getResult();
                        tv.setText("选择影厅和时间：( "+result.size()+" )");
                        LinearLayoutManager manager = new LinearLayoutManager(SeatActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv.setLayoutManager(manager);
                        MovieTingAdapter adapter = new MovieTingAdapter(SeatActivity.this, result);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getD(MovieTingBean.ResultBean bean){
        hallId = bean.getHallId();
        list.clear();
        NetUtils.getInstance().getApis().doZuoWei(Integer.valueOf(hallId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryZuoWeiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryZuoWeiBean bean) {
                        List<QueryZuoWeiBean.ResultBean> result = bean.getResult();
                        for (int i = 0; i < result.size(); i++){
                            SiteBean siteBean = new SiteBean(Integer.valueOf(result.get(i).getRow()), Integer.valueOf(result.get(i).getSeat()), Integer.valueOf(result.get(i).getStatus()));
                            list.add(siteBean);
                        }
                        Toast.makeText(SeatActivity.this, ""+list.size(), Toast.LENGTH_SHORT).show();
                        sele.setData(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
