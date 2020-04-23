package com.bw.movie.model;

import com.bw.movie.bean.UpcomingBean;
import com.bw.movie.contract.UpcommingContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName UpcommingModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2123:25
 */
public class UpcommingModel implements UpcommingContract.IModel {
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
                        callBack.onUpcommingSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onUpcommingError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
