package com.bw.movie.fragment;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.ScheduleAdaoter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ScheduleFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getReasuce() {
        return R.layout.schedulesragment;
    }

    @Override
    protected void getData() {
        String cinemaId = SPUtil.getString(getActivity(), "login", "cinemaId");
        NetUtils.getInstance().getApis().findCinemaScheduleList(Integer.valueOf(cinemaId),1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScheduleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScheduleBean scheduleBean) {
                        List<ScheduleBean.ResultBean> result = scheduleBean.getResult();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                        rv.setLayoutManager(linearLayoutManager);
                        ScheduleAdaoter scheduleAdaoter = new ScheduleAdaoter(getActivity(),result);
                        rv.setAdapter(scheduleAdaoter);
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
