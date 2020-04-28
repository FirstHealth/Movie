package com.bw.movie.model;

import com.bw.movie.bean.MovieCinecisnBean;
import com.bw.movie.contract.CinecismContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName CinecismModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2523:45
 */
public class CinecismModel implements CinecismContract.IModel {
    @Override
    public void getData(int movieid, int page, int count, IModelCallBack callBack) {
        NetUtils.getInstance().getApis().moviecomment(movieid,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCinecisnBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieCinecisnBean bean) {
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
}
