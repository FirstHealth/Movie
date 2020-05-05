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
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private P presenter;
    private Unbinder bind;
//    /**
//     * 控件加载完成的标志
//     */
//    public boolean isChang;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), getReasuce(), null);
        presenter = initPresenter();
        getid(view);
        bind = ButterKnife.bind(this,view);
//        isChang = true;
//        doNetWock();
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("xxx","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("xxx","onCreate");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i("xxx","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("xxx","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("xxx","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("xxx","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("xxx","onDestroyView");
    }

    /**
     * 尝试调用网络，先判断是否对用户可见
     * 如果可见，调用抽象方法，让子类去调接口
     */
//    private void doNetWock(){
//        if(getUserVisibleHint()){
//            getData();
//        }
//    }
//    /**
//     * fragment 提供的回调，回调当天fragment是否对用用户可见
//     * 他是在当这个 fragment 是否对用户的可见发生变化的时候
//     * @param isVisibleToUser false对用户不可见， true对用户可见
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        // 如果还没有加载过数据 && 用户切换到了这个fragment
//        // 那就开始加载数据
//        if(isVisibleToUser&&isChang){
//            getData();
//        }
//    }
}
