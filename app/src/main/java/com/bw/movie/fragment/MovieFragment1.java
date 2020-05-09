package com.bw.movie.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieFragment1 extends BaseFragment {
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
        return R.layout.moviefragment;
    }

    @Override
    protected void getData() {
        String userId = SPUtil.getString(getActivity(), SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_USER_ID);
        String sessionId = SPUtil.getString(getActivity(), SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_SESSION_ID);
        NetUtils.getInstance().getApis().findUserFollowMovieList(Integer.valueOf(userId),sessionId,
                1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserFollowMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserFollowMovieBean userFollowMovieBean) {
                        List<UserFollowMovieBean.ResultBean> result = userFollowMovieBean.getResult();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                        rv.setLayoutManager(linearLayoutManager);
                        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), result);
                        rv.setAdapter(movieAdapter);
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
