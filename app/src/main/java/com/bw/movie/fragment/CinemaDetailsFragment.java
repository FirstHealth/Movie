package com.bw.movie.fragment;

import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindCinemaInfoBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CinemaDetailsFragment extends BaseFragment {
    @BindView(R.id.tv_dz)
    TextView tv_dz;
    @BindView(R.id.tv_dh)
    TextView tv_dh;
    @BindView(R.id.tv_dt)
    TextView tv_dt;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getReasuce() {
        return R.layout.cinemadetailsfragment;
    }

    @Override
    protected void getData() {
        String anInt = SPUtil.getString(getActivity(), "login", "cinemaId");
        NetUtils.getInstance().getApis().findCinemaInfo(Integer.valueOf(anInt))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindCinemaInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindCinemaInfoBean findCinemaInfoBean) {
                        EventBus.getDefault().postSticky(findCinemaInfoBean);
                        FindCinemaInfoBean.ResultBean result = findCinemaInfoBean.getResult();
                        tv_dz.setText(result.getAddress());
                        tv_dh.setText(result.getPhone());
                        tv_dt.setText(result.getVehicleRoute());
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
