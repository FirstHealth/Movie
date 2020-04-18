package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;

/**
 * @ClassName LoginContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1722:49
 */
public interface LoginContract {
    interface IView extends BaseView{
        void getLoginSuccess(LoginBean bean);
        void getLoginError(String str);
    }

    interface IPresenter{
        void getData(String email,String pwd);
    }

    interface IModel{
        void getData(String email,String pwd,IModelCallBack callBack);
        interface IModelCallBack{
            void getLoginSuccess(LoginBean bean);
            void getLoginError(String str);
        }
    }
}
