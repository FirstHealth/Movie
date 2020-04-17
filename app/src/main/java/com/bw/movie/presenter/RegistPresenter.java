package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.RegistContract;
import com.bw.movie.model.RegistModel;

/**
 * @ClassName RegistPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1714:55
 */
public class RegistPresenter extends BasePresenter implements RegistContract.IPresenter {

    private RegistModel model;

    public RegistPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new RegistModel();
    }

    @Override
    public void getRegistData(String name, String pwd, String email, String code) {
        model.getRegistData(name, pwd, email, code, new RegistContract.IModel.IModelRegistCallBack() {
            @Override
            public void doRegistSuccess(RegistBean bean) {
                BaseView view = getView();
                if (view!=null && view instanceof RegistContract.IView){
                    ((RegistContract.IView) view).doRegistSuccess(bean);
                }
            }

            @Override
            public void doRegistError(String str) {
                BaseView view = getView();
                if (view!=null && view instanceof RegistContract.IView){
                    ((RegistContract.IView) view).doRegistError(str);
                }
            }
        });
    }

    @Override
    public void getEmailData(String email) {
        model.getEmailData(email, new RegistContract.IModel.IModelEmailCallBack() {
            @Override
            public void doEmailSuccess(EmailBean bean) {
                BaseView view = getView();
                if (view != null && view instanceof RegistContract.IView){
                    ((RegistContract.IView) view).doEmailSuccess(bean);
                }
            }

            @Override
            public void doEmailError(String str) {
                BaseView view = getView();
                if (view != null && view instanceof RegistContract.IView){
                    ((RegistContract.IView) view).doEmailError(str);
                }
            }
        });
    }
}
