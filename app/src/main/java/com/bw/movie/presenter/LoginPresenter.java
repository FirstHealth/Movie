package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.LoginContract;
import com.bw.movie.model.LoginModel;

/**
 * @ClassName LoginPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1722:53
 */
public class LoginPresenter extends BasePresenter implements LoginContract.IPresenter {

    private LoginModel model;

    public LoginPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new LoginModel();
    }

    @Override
    public void getData(String email, String pwd) {
        model.getData(email, pwd, new LoginContract.IModel.IModelCallBack() {
            @Override
            public void getLoginSuccess(LoginBean bean) {
                BaseView view = getView();
                if (view!=null && view instanceof LoginContract.IView){
                    ((LoginContract.IView) view).getLoginSuccess(bean);
                }
            }

            @Override
            public void getLoginError(String str) {
                BaseView view = getView();
                if (view!=null && view instanceof LoginContract.IView){
                    ((LoginContract.IView) view).getLoginError(str);
                }
            }
        });
    }
}
