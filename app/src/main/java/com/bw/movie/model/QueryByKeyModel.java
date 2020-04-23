package com.bw.movie.model;

import com.bw.movie.bean.QueryByKeyBean;
import com.bw.movie.contract.QueryByKeyContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName QueryByKeyModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2222:40
 */
public class QueryByKeyModel implements QueryByKeyContract.IModel {
    @Override
    public void getData(String keyword, int page, int count, IModelCallBack callBack) {
        NetUtils.getInstance().getApis().doQuery(keyword, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryByKeyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryByKeyBean bean) {
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
