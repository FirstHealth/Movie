package com.bw.movie.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DataListBean;
import com.bw.movie.fragment.ScheduleFragment;
import com.bw.movie.utils.NetUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ScheduleActivity extends BaseActivity {

    @BindView(R.id.iv_fh)
    ImageView iv_fh;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    List<Fragment> list=new ArrayList<>();
    List<String> tablist=new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

    @Override
    protected int getReasuce() {
        return R.layout.activity_schedule;
    }

    @OnClick(R.id.iv_fh)
    public void setIv_fh(){
        finish();
    }

    @Override
    protected void getData()  {
        NetUtils.getInstance().getApis().datalist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataListBean dataListBean) {
                        List<String> result = dataListBean.getResult();
                        for (int i=0;i<result.size();i++){
                            String s = result.get(i);
                            tablist.add(s);
                        }

                        for (int i=0;i<7;i++){
                            ScheduleFragment scheduleFragment = new ScheduleFragment();
                            list.add(scheduleFragment);
                        }
                        MyFragment myFragment = new MyFragment(getSupportFragmentManager());
                        vp.setAdapter(myFragment);

                        tab.addTab(tab.newTab().setText(tablist.get(0)));
                        tab.addTab(tab.newTab().setText(tablist.get(1)));
                        tab.addTab(tab.newTab().setText(tablist.get(2)));
                        tab.addTab(tab.newTab().setText(tablist.get(3)));
                        tab.addTab(tab.newTab().setText(tablist.get(4)));
                        tab.addTab(tab.newTab().setText(tablist.get(5)));
                        tab.addTab(tab.newTab().setText(tablist.get(6)));

                        tab.setupWithViewPager(vp);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
