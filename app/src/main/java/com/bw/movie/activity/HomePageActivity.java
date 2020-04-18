package com.bw.movie.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.MineFragment;
import com.bw.movie.fragment.MovieFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class HomePageActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;

    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<Integer> ivs = new ArrayList<>();
    @Override
    protected int getReasuce() {
        return R.layout.activity_home_page;
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

        tab.setSelectedTabIndicatorHeight(0);

        ivs.clear();
        ivs.add(R.mipmap.yingpian);
        ivs.add(R.mipmap.cinema);
        ivs.add(R.mipmap.my);


        tabs.add("电影");
        tabs.add("影院");
        tabs.add("我的");

        tab.addTab(tab.newTab().setText(""));
        tab.addTab(tab.newTab().setText(""));
        tab.addTab(tab.newTab().setText(""));

        MovieFragment movieFragment = new MovieFragment();
        CinemaFragment cinemaFragment = new CinemaFragment();
        MineFragment mineFragment = new MineFragment();
        list.add(movieFragment);
        list.add(cinemaFragment);
        list.add(mineFragment);

        MyViewPagerFragment pagerFragment = new MyViewPagerFragment(getSupportFragmentManager());
        vp.setAdapter(pagerFragment);

        tab.setupWithViewPager(vp);

        setTabStyle();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @SuppressLint({"ResourceAsColor", "WrongConstant"})
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab != null) {
                    View view = tab.getCustomView();
                    view.setBackgroundColor(R.color.dark);
                    ((TextView) view.findViewById(R.id.tv)).setVisibility(8);//设置一下文字颜色
                    tab.setCustomView(view);
                }
            }

            @SuppressLint({"WrongConstant", "ResourceAsColor"})
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab != null) {
                    View view = tab.getCustomView();
                    view.setBackgroundColor(Color.WHITE);
                    ((TextView) view.findViewById(R.id.tv)).setVisibility(0);//设置一下文字颜色
                    ((TextView) view.findViewById(R.id.tv)).setTextColor(R.color.colormeimei);
                    tab.setCustomView(view);
                }
            }
        });

    }

    public class MyViewPagerFragment extends FragmentPagerAdapter{
        public MyViewPagerFragment(@NonNull FragmentManager fm) {
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

    @SuppressLint("WrongConstant")
    private void setTabStyle() {
        //根据Tab数量循环来设置
        for(int i = 0;i < tabs.size();i++){
            TabLayout.Tab tab = this.tab.getTabAt(i);
            if(tab != null) {
                View view = LayoutInflater.from(this).inflate(R.layout.style_tablayout, null);
                //设置Tab标题
                ((TextView) view.findViewById(R.id.tv)).setText(tabs.get(i));
                ((ImageView) view.findViewById(R.id.iv)).setImageResource(ivs.get(i));
                //第一个默认为选择样式
                if(i == 0) {
                    //将第一个Tab标题颜色设为蓝色
                    ((TextView) view.findViewById(R.id.tv)).setTextColor(getResources().getColor(R.color.colormeimei));
                    //将第一个Tab图标设为蓝色
                    ((ImageView) view.findViewById(R.id.iv)).setImageResource(ivs.get(i));
                }else {
                    //将其他Tab图标设为灰色
                    //((TextView) view.findViewById(R.id.tv)).setVisibility(8);
                }
                //最后添加view到Tab上面
                tab.setCustomView(view);
            }
        }
    }
}
