package com.bw.movie.model;

import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName MovieInModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2223:20
 */
public class MovieInModel implements MovieInContract.IModel {
    @Override
    public void getData(int movieid, IModelCallBack callBack) {
        NetUtils.getInstance().getApis().movieData(movieid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDataBean bean) {
                            callBack.onSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                            callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getGuanData(int movieId, IModelGuanCallBack callBack) {
        NetUtils.getInstance().getApis().doGuan(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistBean bean) {
                        callBack.onGuanSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onGuanError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getQuGuanData(int movieid, IModelQuGuanCallBack callBack) {
        NetUtils.getInstance().getApis().doQuGuan(movieid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistBean bean) {
                        callBack.onQuGuanSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onQuGuanError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
