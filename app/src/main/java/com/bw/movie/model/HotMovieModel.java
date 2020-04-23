package com.bw.movie.model;

import com.bw.movie.bean.HotMovie;
import com.bw.movie.contract.HotMovieContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName HotMovieModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2123:36
 */
public class HotMovieModel implements HotMovieContract.IModel {
    @Override
    public void getHotData(int page, int count, IModelHotCallBack callBack) {
        NetUtils.getInstance().getApis().doHot(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotMovie hotMovie) {
                        callBack.onHotSuccess(hotMovie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onHotError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
