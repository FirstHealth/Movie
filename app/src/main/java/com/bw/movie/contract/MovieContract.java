package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.HotMovie;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.UpcomingBean;

/**
 * @ClassName MovieContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/190:29
 */
public interface MovieContract {
    interface IView extends BaseView{
        void onHotSuccess(HotMovie bean);
        void onHotError(String str);

        void onReleaseingSuccess(ReleaseingMovie bean);
        void onReleaseingError(String str);

        void onUpcommingSuccess(UpcomingBean bean);
        void onUpcommingError(String str);
    }

    interface IPresenter{
        void getHotData(int page,int count);
        void getReleaseingData(int page,int count);
        void getUpcommingData(int page,int count);
    }

    interface IModel{
        void getHotData(int page,int count,IModelHotCallBack callBack);
        interface IModelHotCallBack{
            void onHotSuccess(HotMovie bean);
            void onHotError(String str);
        }

        void getReleaseingData(int page,int count,IModelReleasingCallBack callBack);
        interface IModelReleasingCallBack{
            void onReleaseingSuccess(ReleaseingMovie bean);
            void onReleaseingError(String str);
        }

        void getUpcommingData(int page,int count,IModelUpcommingCallBack callBack);
        interface IModelUpcommingCallBack{
            void onUpcommingSuccess(UpcomingBean bean);
            void onUpcommingError(String str);
        }
    }
}
