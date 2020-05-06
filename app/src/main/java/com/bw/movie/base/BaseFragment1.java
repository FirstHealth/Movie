package com.bw.movie.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
public abstract class BaseFragment1<P extends BasePresenter> extends Fragment {

    private P presenter;
    private Unbinder bind;
    boolean isChen;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), getReasuce(), null);
        presenter = initPresenter();
        getid(view);
        bind = ButterKnife.bind(this, view);
        doNetWock();
        isChen = true;
        return view;
    }

    protected void doNetWock(){
        if (getUserVisibleHint()){
            getData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getData();
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        bind.unbind();
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract int getReasuce();

    protected abstract P initPresenter();

    protected abstract void getid(View view);

    protected abstract void getData();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("xxx", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("xxx", "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("xxx", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("xxx", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("xxx", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("xxx", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("xxx", "onDestroyView");
    }


}
