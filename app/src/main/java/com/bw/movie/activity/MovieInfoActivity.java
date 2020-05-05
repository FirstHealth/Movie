package com.bw.movie.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.bean.movieinfo.ResultBean_movieinfo;
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.ResultBean_movieinfoDao;
import com.bw.movie.fragment.CinecismFragment;
import com.bw.movie.fragment.HeraldFragment;
import com.bw.movie.fragment.IntroduceFragment;
import com.bw.movie.fragment.StillFragment;
import com.bw.movie.presenter.MovieInPresenter;
import com.bw.movie.utils.NetUtils;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName MovieInfoActivity
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2223:04
 */
public class MovieInfoActivity extends BaseActivity implements MovieInContract.IView {

    private String movieid;

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.di)
    TextView di;
    @BindView(R.id.heart)
    ImageView heart;
    @BindView(R.id.guan)
    TextView guan;
    @BindView(R.id.names)
    TextView names;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.bu_write1)
    Button bu1;
    @BindView(R.id.bu_write2)
    Button bu2;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    private ResultBean_movieinfo result;
    private ResultBean_movieinfoDao resultBean_movieinfoDao;

    @Override
    protected int getReasuce() {
        return R.layout.activity_movieinfo;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MovieInPresenter(this);
    }

    @Override
    protected void getId() {

    }

    @Override
    protected void getData() {
        DaoSession movieinfo = DaoMaster.newDevSession(this, "movieinfo1");
        resultBean_movieinfoDao = movieinfo.getResultBean_movieinfoDao();

        Intent intent = getIntent();
        movieid = intent.getStringExtra("movieid");
        Toast.makeText(this, ""+ movieid, Toast.LENGTH_SHORT).show();
        if (NetUtils.getInstance().isNetWork(this)) {
            BasePresenter presenter = getPresenter();
            if (presenter instanceof MovieInPresenter) {
                ((MovieInPresenter) presenter).getData(Integer.valueOf(movieid));
            }
        }else {
            int value = new Long(movieid).intValue();
            Toast.makeText(this, "无网络", Toast.LENGTH_SHORT).show();
            List<ResultBean_movieinfo> list = resultBean_movieinfoDao.queryBuilder()
                    .list();
            for (int i = 0; i < list.size(); i++){
                if (value == list.get(i).getMovieId()){
                    MovieDataBean movieDataBean = new MovieDataBean();
                    movieDataBean.setResult(list.get(i));
                    onSuccess(movieDataBean);
                }
            }
        }

        tabs.add("介绍");
        tabs.add("预告");
        tabs.add("剧照");
        tabs.add("影评");

        tab.setTag(tab.newTab().setText(tabs.get(0)));
        tab.setTag(tab.newTab().setText(tabs.get(1)));
        tab.setTag(tab.newTab().setText(tabs.get(2)));

        IntroduceFragment introduceFragment = new IntroduceFragment();
        HeraldFragment heraldFragment = new HeraldFragment();
        StillFragment stillFragment = new StillFragment();
        CinecismFragment cinecismFragment = new CinecismFragment();
        list.add(introduceFragment);
        list.add(heraldFragment);
        list.add(stillFragment);
        list.add(cinecismFragment);
        MyFragmentViewPager fragmentViewPager = new MyFragmentViewPager(getSupportFragmentManager());
        vp.setAdapter(fragmentViewPager);
        tab.setupWithViewPager(vp);

        vp.setOffscreenPageLimit(4);


    }

    @Override
    public void onSuccess(MovieDataBean bean) {
        result = bean.getResult();

        resultBean_movieinfoDao.insertOrReplaceInTx(result);

        if (result.getWhetherFollow() == 1){
            heart.setImageResource(R.mipmap.emptyheart);
            guan.setText("已关注");
        }else {
            heart.setImageResource(R.mipmap.xxxxxx);
            guan.setText("未关注");
        }
        Glide.with(this).load(result.getImageUrl()).into(iv);
        tv1.setText("评分："+ result.getScore()+"分");
        tv2.setText("评论："+ result.getCommentNum()+"万");

        name.setText(result.getName());
        time.setText(result.getMovieType()+"   "+ result.getDuration());
        long releaseTime = result.getReleaseTime();
                String date = new SimpleDateFormat("yy--MM--dd").format(
                new java.util.Date(releaseTime));
        di.setText("20"+date+"  "+ result.getPlaceOrigin()+"上映");

        names.setText(result.getName());

        EventBus.getDefault().postSticky(result);

    }

    @Override
    public void onError(String str) {

    }

    @Override
    public void onGuanSuccess(RegistBean bean) {
    }

    @Override
    public void onGuanError(String str) {

    }

    @Override
    public void onQuGuanSuccess(RegistBean bean) {
    }

    @Override
    public void onQuGuanError(String str) {

    }

    public class MyFragmentViewPager extends FragmentPagerAdapter{
        public MyFragmentViewPager(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

    @OnClick(R.id.heart)
    public void onClick(View view){
        int whetherFollow = result.getWhetherFollow();
        if (whetherFollow == 2){
            Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
            heart.setImageResource(R.mipmap.emptyheart);
            guan.setText("已关注");
            BasePresenter presenter1 = getPresenter();
            if (presenter1 instanceof MovieInPresenter){
                ((MovieInPresenter) presenter1).getGuanData(Integer.valueOf(movieid));
            }

        }
        if (whetherFollow == 1){
            heart.setImageResource(R.mipmap.xxxxxx);
            guan.setText("未关注");
            BasePresenter presenter2 = getPresenter();
            if (presenter2 instanceof MovieInPresenter){
                ((MovieInPresenter) presenter2).getQuGuanData(Integer.valueOf(movieid));
            }
        }
    }

    @OnClick({R.id.bu_write1,R.id.bu_write2})
    public void getd(View view){
        switch (view.getId()){
            case R.id.bu_write1:
                String s = name.getText().toString();
                Intent intent = new Intent(this, WritePingLunActivity.class);
                intent.putExtra("id",movieid+"");
                intent.putExtra("name",s+"");
                startActivity(intent);
                break;
            case R.id.bu_write2:
                String s1 = name.getText().toString();
                Intent intent1 = new Intent(this, WritePingLunActivity.class);
                intent1.putExtra("id",movieid+"");
                intent1.putExtra("name",s1+"");
                startActivity(intent1);
                break;
        }
    }

}
