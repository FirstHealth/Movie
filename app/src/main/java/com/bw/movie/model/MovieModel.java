package com.bw.movie.model;

import com.bw.movie.bean.HotMovie;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.UpcomingBean;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName MovieModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/190:36
 */
public class MovieModel implements MovieContract.IModel {
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
                        if (callBack != null){
                            callBack.onHotSuccess(hotMovie);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null){
                            callBack.onHotError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getReleaseingData(int page, int count, IModelReleasingCallBack callBack) {
        NetUtils.getInstance().getApis().doReleaseing(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseingMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReleaseingMovie releaseingMovie) {
                        if (callBack != null){
                            callBack.onReleaseingSuccess(releaseingMovie);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null){
                            callBack.onReleaseingError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getUpcommingData(int page, int count, IModelUpcommingCallBack callBack) {
        NetUtils.getInstance().getApis().doUpcoming(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpcomingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpcomingBean bean) {
                        if (callBack!=null){
                            callBack.onUpcommingSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack!=null){
                            callBack.onUpcommingError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
