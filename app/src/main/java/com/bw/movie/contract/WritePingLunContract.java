package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.RegistBean;

/**
 * @ClassName WritePingLunContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/280:04
 */
public interface WritePingLunContract {

    interface IView extends BaseView{
        void onSuccess(RegistBean bean);
        void onError(String str);
    }

    interface IPresenter{
        void getData(int id,String content,double score);
    }

    interface IModel{
        void getData(int id,String content,double score,IModelCallBack callBack);
        interface IModelCallBack{
            void onSuccess(RegistBean bean);
            void onError(String str);
        }
    }

}
