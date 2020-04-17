package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegistBean;

/**
 * @ClassName RegistContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1714:47
 */
public interface RegistContract {
    interface IView extends BaseView{
        void doRegistSuccess(RegistBean bean);
        void doRegistError(String str);

        void doEmailSuccess(EmailBean bean);
        void doEmailError(String str);
    }

    interface IPresenter{
        void getRegistData(String name,String pwd,String email,String code);
        void getEmailData(String email);
    }

    interface IModel{
        void getRegistData(String name,String pwd,String email,String code,IModelRegistCallBack callBack);
        interface IModelRegistCallBack{
            void doRegistSuccess(RegistBean bean);
            void doRegistError(String str);
        }

        void getEmailData(String email,IModelEmailCallBack callBack);
        interface IModelEmailCallBack{
            void doEmailSuccess(EmailBean bean);
            void doEmailError(String str);
        }
    }
}
