package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.HotMovieFragment;
import com.bw.movie.fragment.ReseaingFragment;
import com.bw.movie.fragment.UpCommingFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName MovieListActivity
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2121:55
 */
public class MovieListActivity extends BaseActivity {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.search)
    ImageView iv;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    @Override
    protected int getReasuce() {
        return R.layout.activity_movielist;
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

        Intent intent = getIntent();
        String index = intent.getStringExtra("x");

        tabs.clear();
        list.clear();
        tabs.add("正在热映");
        tabs.add("即将上映");
        tabs.add("热门电影");

        tab.setTag(tab.newTab().setText(tabs.get(0)));
        tab.setTag(tab.newTab().setText(tabs.get(1)));
        tab.setTag(tab.newTab().setText(tabs.get(2)));

        ReseaingFragment reseaingFragment = new ReseaingFragment();
        UpCommingFragment upCommingFragment = new UpCommingFragment();
        HotMovieFragment hotMovieFragment = new HotMovieFragment();
        list.add(reseaingFragment);
        list.add(upCommingFragment);
        list.add(hotMovieFragment);

        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);

        tab.setupWithViewPager(vp);

        tab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (index != null) {
            Toast.makeText(this, ""+index, Toast.LENGTH_SHORT).show();
            vp.setCurrentItem(Integer.valueOf(index));
        }

    }

    @OnClick(R.id.search)
    public void onClick(View view){
//        iv.setVisibility(8);
//        et.setVisibility(0);
        String str = et.getText().toString();
        Intent intent = new Intent(MovieListActivity.this, QueryByKeyActivity.class);
        intent.putExtra("key",str);
        startActivity(intent);
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter{
        public MyViewPagerAdapter(@NonNull FragmentManager fm) {
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
