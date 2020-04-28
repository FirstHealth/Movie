package com.bw.movie.base;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName MyBaseActivity
 * @Description TODO
 * @Author tys
 * @Date 2020/3/614:43
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private P presenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getReasuce());
        bind = ButterKnife.bind(this);
        presenter = initPresenter();

        getId();

        getData();
    }

    public P getPresenter(){
        if (presenter != null) {
            return presenter;
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
            presenter = null;
        }
        bind.unbind();
    }

    protected abstract int getReasuce();

    protected abstract P initPresenter();

    protected abstract void getId();

    protected abstract void getData();
}
