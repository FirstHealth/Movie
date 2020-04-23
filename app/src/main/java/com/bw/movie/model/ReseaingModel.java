package com.bw.movie.model;

import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.contract.ReseaingContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName ReseaingModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2122:40
 */
public class ReseaingModel implements ReseaingContract.IModel {
    @Override
    public void getReleaseingData(int page, int count, IModelReleasingCallBack callBack) {
        NetUtils.getInstance().getApis().doReleaseing(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseingMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReleaseingMovie releaseingMovie) {
                        callBack.onReleaseingSuccess(releaseingMovie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onReleaseingError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
