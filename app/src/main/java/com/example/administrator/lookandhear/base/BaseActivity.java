package com.example.administrator.lookandhear.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import butterknife.ButterKnife;

/**
 * Created by XuYanping on 2017/9/21.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPresenter() != null) {
            mPresenter = getPresenter();
            mPresenter.attachView((V) this);
        }
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        requestData();
    }

    protected abstract int getLayoutId();

    protected abstract T getPresenter();

    protected abstract Toolbar getToolbar();

    protected abstract String setTitle();

    protected abstract boolean canBack();

    protected void initView() {
        if (getToolbar() != null) {
            mToolbar = getToolbar();
        }
        if (mToolbar != null) {
            if (setTitle() != null && !TextUtils.isEmpty(setTitle())) {
                setTitle(setTitle());
            }
            setSupportActionBar(mToolbar);
            if (canBack()) {
                ActionBar mActionBar = getSupportActionBar();
                if (mActionBar != null) {
                    mActionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
    }

    protected void requestData() {
    }
}
