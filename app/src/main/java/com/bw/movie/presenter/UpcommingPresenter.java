package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.UpcomingBean;
import com.bw.movie.contract.UpcommingContract;
import com.bw.movie.model.UpcommingModel;

/**
 * @ClassName UpcommingPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2123:26
 */
public class UpcommingPresenter extends BasePresenter implements UpcommingContract.IPresenter {

    private UpcommingModel model;

    public UpcommingPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new UpcommingModel();
    }

    @Override
    public void getUpcommingData(int page, int count) {
        model.getUpcommingData(page, count, new UpcommingContract.IModel.IModelUpcommingCallBack() {
            @Override
            public void onUpcommingSuccess(UpcomingBean bean) {
                BaseView view = getView();
                if (view instanceof UpcommingContract.IView){
                    ((UpcommingContract.IView) view).onUpcommingSuccess(bean);
                }
            }

            @Override
            public void onUpcommingError(String str) {
                BaseView view = getView();
                if (view instanceof UpcommingContract.IView){
                    ((UpcommingContract.IView) view).onUpcommingError(str);
                }
            }
        });
    }
}
