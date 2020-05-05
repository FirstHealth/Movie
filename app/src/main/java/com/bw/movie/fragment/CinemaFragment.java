package com.bw.movie.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.cinema.AreaFragment;
import com.bw.movie.fragment.cinema.NearbyFragment;
import com.bw.movie.fragment.cinema.RecommendFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @ClassName MovieFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1823:02
 */
public class CinemaFragment extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    @Override
    protected int getReasuce() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getid(View view) {

    }

    @Override
    protected void getData() {
        tabs.clear();
        list.clear();
        tabs.add("推荐影院");
        tabs.add("附近影院");
        tabs.add("海淀区👌");

        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));

        RecommendFragment recommendFragment = new RecommendFragment();
        NearbyFragment nearbyFragment = new NearbyFragment();
        AreaFragment areaFragment = new AreaFragment();

        list.add(recommendFragment);
        list.add(nearbyFragment);
        list.add(areaFragment);

        MyFragmentViewPager fragmentViewPager = new MyFragmentViewPager(getChildFragmentManager());
        vp.setAdapter(fragmentViewPager);
        tab.setupWithViewPager(vp);

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
