package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.LoginContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName LoginModel
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1722:51
 */
public class LoginModel implements LoginContract.IModel {
    @Override
    public void getData(String email, String pwd, IModelCallBack callBack) {
        NetUtils.getInstance().getApis().doLogin(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        callBack.getLoginSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getLoginError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
