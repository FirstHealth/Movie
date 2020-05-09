package com.bw.movie.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaFragmentAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindUserFollowCinemaListBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CinemaFragment1 extends BaseFragment {
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
        return R.layout.gzcinemafragment;
    }

    @Override
    protected void getData() {
        String userId = SPUtil.getString(getActivity(), SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_USER_ID);
        String sessionId = SPUtil.getString(getActivity(), SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_SESSION_ID);
        NetUtils.getInstance().getApis().findUserFollowCinemaList(Integer.valueOf(userId),sessionId,1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindUserFollowCinemaListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindUserFollowCinemaListBean findUserFollowCinemaListBean) {
                        List<FindUserFollowCinemaListBean.ResultBean> result = findUserFollowCinemaListBean.getResult();
                        Log.i("xxxx",result.size()+"");
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                        rv.setLayoutManager(linearLayoutManager);
                        CinemaFragmentAdapter cinemaFragmentAdapter = new CinemaFragmentAdapter(getActivity(), result);
                        rv.setAdapter(cinemaFragmentAdapter);
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
