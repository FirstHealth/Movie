package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.MovieDataBean;
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
}
