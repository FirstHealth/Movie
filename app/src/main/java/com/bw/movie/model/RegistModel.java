package com.bw.movie.model;

import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.RegistContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName RegistModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1714:52
 */
public class RegistModel implements RegistContract.IModel {
    @Override
    public void getRegistData(String name, String pwd, String email, String code, IModelRegistCallBack callBack) {
        NetUtils.getInstance().getApis().doRegist(name,pwd,email,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistBean bean) {
                        callBack.doRegistSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.doRegistError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getEmailData(String email, IModelEmailCallBack callBack) {
        NetUtils.getInstance().getApis().doEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmailBean bean) {
                        callBack.doEmailSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.doEmailError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
