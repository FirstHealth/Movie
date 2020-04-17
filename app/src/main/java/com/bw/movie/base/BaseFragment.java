package com.bw.movie.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName MyBaseFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/3/615:17
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private P presenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getReasuce(), null);
        presenter = initPresenter();
        getid(view);
        bind = ButterKnife.bind(this,view);
        getData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
            presenter = null;
        }
        bind.unbind();
    }

    public P getPresenter(){
        return presenter;
    }

    protected abstract int getReasuce();

    protected abstract P initPresenter();

    protected abstract void getid(View view);

    protected abstract void getData();
}
