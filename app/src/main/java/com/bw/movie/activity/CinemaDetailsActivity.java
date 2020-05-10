package com.bw.movie.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindCinemaInfoBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.fragment.CinemaDetailsFragment;
import com.bw.movie.fragment.TheaterevaluationFragment;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CinemaDetailsActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv_gz)
    ImageView iv_gz;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.bt_pq)
    Button bt_pq;
    List<Fragment> list=new ArrayList<>();
    List<String> tabList=new ArrayList<>();
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

    @Override
    protected int getReasuce() {
        return R.layout.activity_cinema_details;
    }

    @Override
    protected void getData() {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        CinemaDetailsFragment cinemaDetailsFragment = new CinemaDetailsFragment();
        TheaterevaluationFragment theaterevaluationFragment = new TheaterevaluationFragment();
        list.add(cinemaDetailsFragment);
        list.add(theaterevaluationFragment);
        MyFragment myFragment = new MyFragment(getSupportFragmentManager());
        vp.setAdapter(myFragment);

        tabList.add("影院详情");
        tabList.add("影院评价");

        tab.addTab(tab.newTab().setText(tabList.get(0)));
        tab.addTab(tab.newTab().setText(tabList.get(1)));

        tab.setupWithViewPager(vp);
    }

    @OnClick(R.id.iv_gz)
    public void setIv_gz(){
        iv_gz.setImageResource(R.mipmap.emptyheart);
        String anInt = SPUtil.getString(this, "login", "cinemaId");
        String userId = SPUtil.getString(this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_USER_ID);
        String sessionId = SPUtil.getString(this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_SESSION_ID);
        NetUtils.getInstance().getApis().followCinema(Integer.valueOf(userId),sessionId,Integer.valueOf(anInt))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistBean emailCodeBean) {
                        Toast.makeText(CinemaDetailsActivity.this, ""+emailCodeBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.bt_pq)
    public void setBt_pq(){
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv)
    public void ivOnCLick(){
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getBean(FindCinemaInfoBean findCinemaInfoBean){
        tv_name.setText(findCinemaInfoBean.getResult().getName());
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
            return tabList.get(position);
        }
    }

}
