package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.MovieCinecisnBean;

/**
 * @ClassName CinecismContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2523:37
 */
public interface CinecismContract {
    interface IView extends BaseView{
        void onSuccess(MovieCinecisnBean bean);
        void onError(String str);
    }

    interface IPresenter{
        void getData(int movieid,int page,int count);
    }

    interface IModel{
        void getData(int movieid,int page,int count,IModelCallBack callBack);
        interface IModelCallBack{
            void onSuccess(MovieCinecisnBean bean);
            void onError(String str);
        }
    }
}
