package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.ReleaseingMovie;

/**
 * @ClassName ReseaingContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2122:38
 */
public interface ReseaingContract {
    interface IView extends BaseView {
        void onReleaseingSuccess(ReleaseingMovie bean);
        void onReleaseingError(String str);
    }

    interface IPresenter{
        void getReleaseingData(int page,int count);
    }

    interface IModel{
        void getReleaseingData(int page,int count,IModelReleasingCallBack callBack);
        interface IModelReleasingCallBack{
            void onReleaseingSuccess(ReleaseingMovie bean);
            void onReleaseingError(String str);
        }

    }
}
