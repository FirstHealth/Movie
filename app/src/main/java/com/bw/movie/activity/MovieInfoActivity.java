package com.bw.movie.activity;

import android.content.Intent;
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
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.fragment.CinecismFragment;
import com.bw.movie.fragment.HeraldFragment;
import com.bw.movie.fragment.IntroduceFragment;
import com.bw.movie.fragment.StillFragment;
import com.bw.movie.presenter.MovieInPresenter;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;

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
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
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
        Intent intent = getIntent();
        movieid = intent.getStringExtra("movieid");
        Toast.makeText(this, ""+ movieid, Toast.LENGTH_SHORT).show();

        BasePresenter presenter = getPresenter();
        if (presenter instanceof MovieInPresenter){
            ((MovieInPresenter) presenter).getData(Integer.valueOf(movieid));
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



    }

    @Override
    public void onSuccess(MovieDataBean bean) {
        MovieDataBean.ResultBean result = bean.getResult();

        Glide.with(this).load(result.getImageUrl()).into(iv);
        tv1.setText("评分："+result.getScore()+"分");
        tv2.setText("评论："+result.getCommentNum()+"万");

        name.setText(result.getName());
        time.setText(result.getMovieType()+"   "+result.getDuration());
        long releaseTime = result.getReleaseTime();
                String date = new SimpleDateFormat("yy--MM--dd").format(
                new java.util.Date(releaseTime));
        di.setText("20"+date+"  "+result.getPlaceOrigin()+"上映");

        names.setText(result.getName());

        EventBus.getDefault().postSticky(result);

    }

    @Override
    public void onError(String str) {

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
}
