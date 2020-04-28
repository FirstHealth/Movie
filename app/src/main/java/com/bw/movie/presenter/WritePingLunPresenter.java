package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.WritePingLunContract;
import com.bw.movie.model.WritePingLunModel;

/**
 * @ClassName WritePingLunPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/280:06
 */
public class WritePingLunPresenter extends BasePresenter implements WritePingLunContract.IPresenter {

    private WritePingLunModel model;

    public WritePingLunPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new WritePingLunModel();
    }

    @Override
    public void getData(int id, String content, double score) {
        model.getData(id, content, score, new WritePingLunContract.IModel.IModelCallBack() {
            @Override
            public void onSuccess(RegistBean bean) {
                BaseView view = getView();
                if (view instanceof WritePingLunContract.IView){
                    ((WritePingLunContract.IView) view).onSuccess(bean);
                }
            }

            @Override
            public void onError(String str) {
                BaseView view = getView();
                if (view instanceof WritePingLunContract.IView){
                    ((WritePingLunContract.IView) view).onError(str);
                }
            }
        });
    }
}
