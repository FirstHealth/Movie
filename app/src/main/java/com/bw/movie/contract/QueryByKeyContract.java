package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.QueryByKeyBean;

/**
 * @ClassName QueryByKeyContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2222:37
 */
public interface QueryByKeyContract {
    interface IView extends BaseView{
        void onSuccess(QueryByKeyBean bean);
        void onError(String str);
    }

    interface IPresenter{
        void getData(String keyword,int page,int count);
    }

    interface IModel{
        void getData(String keyword,int page,int count,IModelCallBack callBack);
        interface IModelCallBack{
            void onSuccess(QueryByKeyBean bean);
            void onError(String str);
        }
    }
}
