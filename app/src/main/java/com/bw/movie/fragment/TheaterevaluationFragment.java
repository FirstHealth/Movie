package com.bw.movie.fragment;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.TheaterevaluationAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindAllCinemaCommentBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TheaterevaluationFragment extends BaseFragment {
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
        return R.layout.theaterevaluationfragment;
    }

    @Override
    protected void getData() {
        String anInt = SPUtil.getString(getActivity(), "login", "cinemaId");
        NetUtils.getInstance().getApis().findAllCinemaComment(Integer.valueOf(anInt),1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindAllCinemaCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindAllCinemaCommentBean findAllCinemaCommentBean) {
                        List<FindAllCinemaCommentBean.ResultBean> result = findAllCinemaCommentBean.getResult();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                        rv.setLayoutManager(linearLayoutManager);
                        TheaterevaluationAdapter theaterevaluationAdapter = new TheaterevaluationAdapter(getActivity(), result);
                        rv.setAdapter(theaterevaluationAdapter);
                        theaterevaluationAdapter.setOnClick(new TheaterevaluationAdapter.OnClick() {
                            @Override
                            public void setint(int id) {
                                String userId = SPUtil.getString(getActivity(), SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_USER_ID);
                                String sessionId = SPUtil.getString(getActivity(), SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_SESSION_ID);
                                NetUtils.getInstance().getApis().cinemaCommentGreat(Integer.valueOf(userId),sessionId,id)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<RegistBean>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(RegistBean emailCodeBean) {
                                                Toast.makeText(getActivity(), ""+emailCodeBean.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }

                                            @Override
                                            public void onComplete() {

                                            }
                                        });
                            }
                        });
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
