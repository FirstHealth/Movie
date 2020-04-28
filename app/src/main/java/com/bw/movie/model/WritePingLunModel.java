package com.bw.movie.model;

import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.WritePingLunContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName WritePingLunModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/280:07
 */
public class WritePingLunModel implements WritePingLunContract.IModel {
    @Override
    public void getData(int id, String content, double score, IModelCallBack callBack) {
        NetUtils.getInstance().getApis().doWritePing(id,content,score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistBean bean) {
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
