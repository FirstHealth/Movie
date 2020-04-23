package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.MovieDataBean;

/**
 * @ClassName MovieInContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2223:15
 */
public interface MovieInContract {
    interface IView extends BaseView{
        void onSuccess(MovieDataBean bean);
        void onError(String str);
    }

    interface IPresenter{
        void getData(int movieid);
    }

    interface IModel{
        void getData(int movieid,IModelCallBack callBack);
        interface IModelCallBack{
            void onSuccess(MovieDataBean bean);
            void onError(String str);
        }
    }
}
