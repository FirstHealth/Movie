package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegistBean;

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

        void onGuanSuccess(RegistBean bean);
        void onGuanError(String str);

        void onQuGuanSuccess(RegistBean bean);
        void onQuGuanError(String str);
    }

    interface IPresenter{
        void getData(int movieid);
        void getGuanData(int movieId);
        void getQuGuanData(int movieid);
    }

    interface IModel{
        void getData(int movieid,IModelCallBack callBack);
        interface IModelCallBack{
            void onSuccess(MovieDataBean bean);
            void onError(String str);
        }
        void getGuanData(int movieId,IModelGuanCallBack callBack);
        interface IModelGuanCallBack{
            void onGuanSuccess(RegistBean bean);
            void onGuanError(String str);
        }
        void getQuGuanData(int movieid,IModelQuGuanCallBack callBack);
        interface IModelQuGuanCallBack{
            void onQuGuanSuccess(RegistBean bean);
            void onQuGuanError(String str);
        }
    }
}
