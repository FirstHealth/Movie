package com.bw.movie.contract;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.HotMovie;

/**
 * @ClassName HotMovieContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2123:35
 */
public interface HotMovieContract {
    interface IView extends BaseView {
        void onHotSuccess(HotMovie bean);
        void onHotError(String str);
    }

    interface IPresenter{
        void getHotData(int page,int count);
    }

    interface IModel{
        void getHotData(int page,int count,IModelHotCallBack callBack);
        interface IModelHotCallBack{
            void onHotSuccess(HotMovie bean);
            void onHotError(String str);
        }
    }

}
