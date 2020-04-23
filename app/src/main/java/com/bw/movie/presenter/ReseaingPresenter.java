package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.contract.ReseaingContract;
import com.bw.movie.model.ReseaingModel;

/**
 * @ClassName ReseaingPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2122:41
 */
public class ReseaingPresenter extends BasePresenter implements ReseaingContract.IPresenter {

    private ReseaingModel model;

    public ReseaingPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new ReseaingModel();
    }

    @Override
    public void getReleaseingData(int page, int count) {
        model.getReleaseingData(page, count, new ReseaingContract.IModel.IModelReleasingCallBack() {
            @Override
            public void onReleaseingSuccess(ReleaseingMovie bean) {
                BaseView view = getView();
                if (view instanceof ReseaingContract.IView){
                    ((ReseaingContract.IView) view).onReleaseingSuccess(bean);
                }
            }

            @Override
            public void onReleaseingError(String str) {
                BaseView view = getView();
                if (view instanceof ReseaingContract.IView){
                    ((ReseaingContract.IView) view).onReleaseingError(str);
                }
            }
        });
    }
}
