package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.QueryByKeyBean;
import com.bw.movie.contract.QueryByKeyContract;
import com.bw.movie.model.QueryByKeyModel;

/**
 * @ClassName QueryByKeyPresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2222:41
 */
public class QueryByKeyPresenter extends BasePresenter implements QueryByKeyContract.IPresenter {

    private QueryByKeyModel model;

    public QueryByKeyPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        model = new QueryByKeyModel();
    }

    @Override
    public void getData(String keyword, int page, int count) {
        model.getData(keyword, page, count, new QueryByKeyContract.IModel.IModelCallBack() {
            @Override
            public void onSuccess(QueryByKeyBean bean) {
                BaseView view = getView();
                if (view instanceof  QueryByKeyContract.IView){
                    ((QueryByKeyContract.IView) view).onSuccess(bean);
                }
            }

            @Override
            public void onError(String str) {
                BaseView view = getView();
                if (view instanceof  QueryByKeyContract.IView){
                    ((QueryByKeyContract.IView) view).onError(str);
                }
            }
        });
    }
}
