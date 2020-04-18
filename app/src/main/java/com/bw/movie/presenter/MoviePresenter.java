package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.HotMovie;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.UpcomingBean;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.model.MovieModel;

/**
 * @ClassName MoviePresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/190:40
 */
public class MoviePresenter extends BasePresenter implements MovieContract.IPresenter {

    private MovieModel model;

    public MoviePresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new MovieModel();
    }

    @Override
    public void getHotData(int page, int count) {
        model.getHotData(page, count, new MovieContract.IModel.IModelHotCallBack() {
            @Override
            public void onHotSuccess(HotMovie bean) {
                BaseView view = getView();
                if (view!= null && view instanceof MovieContract.IView){
                    ((MovieContract.IView) view).onHotSuccess(bean);
                }
            }

            @Override
            public void onHotError(String str) {
                BaseView view = getView();
                if (view!= null && view instanceof MovieContract.IView){
                    ((MovieContract.IView) view).onHotError(str);
                }
            }
        });
    }

    @Override
    public void getReleaseingData(int page, int count) {
        model.getReleaseingData(page, count, new MovieContract.IModel.IModelReleasingCallBack() {
            @Override
            public void onReleaseingSuccess(ReleaseingMovie bean) {
                BaseView view = getView();
                if (view!=null && view instanceof MovieContract.IView){
                    ((MovieContract.IView) view).onReleaseingSuccess(bean);
                }
            }

            @Override
            public void onReleaseingError(String str) {
                BaseView view = getView();
                if (view!=null && view instanceof MovieContract.IView){
                    ((MovieContract.IView) view).onReleaseingError(str);
                }
            }
        });
    }

    @Override
    public void getUpcommingData(int page, int count) {
        model.getUpcommingData(page, count, new MovieContract.IModel.IModelUpcommingCallBack() {
            @Override
            public void onUpcommingSuccess(UpcomingBean bean) {
                BaseView view = getView();
                if (view!=null && view instanceof MovieContract.IView){
                    ((MovieContract.IView) view).onUpcommingSuccess(bean);
                }
            }

            @Override
            public void onUpcommingError(String str) {
                BaseView view = getView();
                if (view!=null && view instanceof MovieContract.IView){
                    ((MovieContract.IView) view).onUpcommingError(str);
                }
            }
        });
    }
}
