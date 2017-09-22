package com.example.administrator.lookandhear.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by XuYanping on 2017/9/22.
 */

public class BasePresenter<V> {

    protected Reference<V> mReference;

    public void attachView(V view) {
        mReference = new WeakReference<V>(view);
    }

    protected V getView() {
        return mReference.get();
    }

    protected void detachView() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
    }
}
