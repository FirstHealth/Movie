package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.model.MovieInModel;

/**
 * @ClassName MovieInPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2223:21
 */
public class MovieInPresenter extends BasePresenter implements MovieInContract.IPresenter {

    private MovieInModel model;

    public MovieInPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new MovieInModel();
    }

    @Override
    public void getData(int movieid) {
        model.getData(movieid, new MovieInContract.IModel.IModelCallBack() {
            @Override
            public void onSuccess(MovieDataBean bean) {
                BaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onSuccess(bean);
                }
            }

            @Override
            public void onError(String str) {
                BaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onError(str);
                }
            }
        });
    }

    @Override
    public void getGuanData(int movieId) {
        model.getGuanData(movieId, new MovieInContract.IModel.IModelGuanCallBack() {
            @Override
            public void onGuanSuccess(RegistBean bean) {
                BaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onGuanSuccess(bean);
                }
            }

            @Override
            public void onGuanError(String str) {
                BaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onGuanError(str);
                }
            }
        });
    }

    @Override
    public void getQuGuanData(int movieid) {
        model.getQuGuanData(movieid, new MovieInContract.IModel.IModelQuGuanCallBack() {
            @Override
            public void onQuGuanSuccess(RegistBean bean) {
                BaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onQuGuanSuccess(bean);
                }
            }

            @Override
            public void onQuGuanError(String str) {
                BaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onQuGuanError(str);
                }
            }
        });
    }
}
