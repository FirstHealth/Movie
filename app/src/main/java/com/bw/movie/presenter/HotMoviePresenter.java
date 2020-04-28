package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.HotMovie;
import com.bw.movie.contract.HotMovieContract;
import com.bw.movie.model.HotMovieModel;

/**
 * @ClassName HotMoviePresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2123:37
 */
public class HotMoviePresenter extends BasePresenter implements HotMovieContract.IPresenter {

    private HotMovieModel model;

    public HotMoviePresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new HotMovieModel();
    }

    @Override
    public void getHotData(int page, int count) {
        model.getHotData(page, count, new HotMovieContract.IModel.IModelHotCallBack() {
            @Override
            public void onHotSuccess(HotMovie bean) {
                BaseView view = getView();
                if (view instanceof HotMovieContract.IView){
                    ((HotMovieContract.IView) view).onHotSuccess(bean);

                }
            }

            @Override
            public void onHotError(String str) {
                BaseView view = getView();
                if (view instanceof HotMovieContract.IView){
                    ((HotMovieContract.IView) view).onHotError(str);
                }
            }
        });
    }
}
