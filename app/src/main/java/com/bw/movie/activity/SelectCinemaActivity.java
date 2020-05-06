package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.bw.movie.R;
import com.bw.movie.adapter.QueryCinemaAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.QueryByKeyBean;
import com.bw.movie.bean.QueryCinemaBean;
import com.bw.movie.utils.NetUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SelectCinemaActivity extends BaseActivity {
    @BindView(R.id.vv)
    VideoView vv;
    @BindView(R.id.name)
    TextView na;
    @BindView(R.id.score)
    TextView sc;
    @BindView(R.id.dao)
    TextView dao;
    @BindView(R.id.time)
    TextView ti;
    @BindView(R.id.bb)
    TextView bb;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.rv)
    RecyclerView rv;
    ArrayList<String> tabs = new ArrayList<>();
    private String id;

    @Override
    protected int getReasuce() {
        return R.layout.activity_select_cinema;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String score = intent.getStringExtra("score");
        String dao = intent.getStringExtra("dao");
        id = intent.getStringExtra("id");
        String ima = intent.getStringExtra("ima");
        String time = intent.getStringExtra("time");

        na.setText(name);
        sc.setText(score+"分");
        this.dao.setText(dao);
        ti.setText(time);

        Uri uri = Uri.parse(ima);
        vv.setVideoURI(uri);

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.start();
            }
        });

        tabs.clear();
        tabs.add("海淀区");
        tabs.add("今天 05-14");
        tabs.add("价格最低");
        tabs.add("🍭");

        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.addTab(tab.newTab().setText(tabs.get(3)));

    }

    @Override
    protected void getData() {
        NetUtils.getInstance().getApis().doQueryBean(Integer.valueOf(id),10,1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryCinemaBean bean) {
                        List<QueryCinemaBean.ResultBean> result = bean.getResult();
                        LinearLayoutManager manager = new LinearLayoutManager(SelectCinemaActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv.setLayoutManager(manager);
                        QueryCinemaAdapter adapter = new QueryCinemaAdapter(SelectCinemaActivity.this, result,id);
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
}
