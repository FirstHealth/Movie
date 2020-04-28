package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.MovieCinecisnBean;
import com.bw.movie.contract.CinecismContract;
import com.bw.movie.model.CinecismModel;

/**
 * @ClassName CinecismPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2523:46
 */
public class CinecismPresenter extends BasePresenter implements CinecismContract.IPresenter {

    private CinecismModel model;

    public CinecismPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new CinecismModel();
    }

    @Override
    public void getData(int movieid, int page, int count) {
        model.getData(movieid, page, count, new CinecismContract.IModel.IModelCallBack() {
            @Override
            public void onSuccess(MovieCinecisnBean bean) {
                BaseView view = getView();
                if (view instanceof CinecismContract.IView){
                    ((CinecismContract.IView) view).onSuccess(bean);
                }
            }

            @Override
            public void onError(String str) {
                BaseView view = getView();
                if (view instanceof CinecismContract.IView){
                    ((CinecismContract.IView) view).onError(str);
                }
            }
        });
    }
}
