package com.bw.movie.base;

import java.lang.ref.WeakReference;

/**
 * @ClassName MyBasePresenter
 * @Description TODO
 * @Author tys
 * @Date 2020/3/614:44
 */
public abstract class BasePresenter<V extends BaseView> {

    private WeakReference<V> weakReference;

    public BasePresenter(V v) {
        weakReference = new WeakReference<>(v);
        initModel();
    }

    public V getView(){
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public void detachView(){
        if (weakReference != null){
            weakReference.clear();
            weakReference = null;
        }
    }

    protected abstract void initModel();
}
