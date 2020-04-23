package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.UpcomingBean;

/**
 * @ClassName UpcommingContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2123:24
 */
public interface UpcommingContract {
    interface IView extends BaseView {
        void onUpcommingSuccess(UpcomingBean bean);
        void onUpcommingError(String str);
    }

    interface IPresenter{
        void getUpcommingData(int page,int count);
    }

    interface IModel{
        void getUpcommingData(int page,int count,IModelUpcommingCallBack callBack);
        interface IModelUpcommingCallBack{
            void onUpcommingSuccess(UpcomingBean bean);
            void onUpcommingError(String str);
        }
    }
}
