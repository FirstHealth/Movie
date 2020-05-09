package com.bw.movie.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;


import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.CinemaFragment1;
import com.bw.movie.fragment.MovieFragment;
import com.bw.movie.fragment.MovieFragment1;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFocusActivity extends BaseActivity {
    @BindView(R.id.iv_fh)
    ImageView iv_fh;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    List<Fragment>  list=new ArrayList<>();
    List<String>  tablist=new ArrayList<>();

    @Override
    protected int getReasuce() {
        return com.bw.movie.R.layout.activity_my_focus;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void getId() {

    }


    @Override
    protected void getData() {
        MovieFragment1 movieFragment = new MovieFragment1();
        CinemaFragment1 cinemaFragment = new CinemaFragment1();
        list.add(movieFragment);
        list.add(cinemaFragment);
        MyFragment myFragment = new MyFragment(getSupportFragmentManager());
        vp.setAdapter(myFragment);

        tablist.add("电影");
        tablist.add("影院");

        tab.addTab(tab.newTab().setText(tablist.get(0)));
        tab.addTab(tab.newTab().setText(tablist.get(1)));

        tab.setupWithViewPager(vp);
    }

    @OnClick(R.id.iv_fh)
    public void setIv_fh(){
        finish();
    }

    public class MyFragment extends FragmentPagerAdapter{

        public MyFragment(@NonNull FragmentManager fm) {
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
            return tablist.get(position);
        }
    }
}
