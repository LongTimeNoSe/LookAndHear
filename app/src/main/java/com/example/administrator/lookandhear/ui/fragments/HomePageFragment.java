package com.example.administrator.lookandhear.ui.fragments;

import android.util.Log;

import com.example.administrator.lookandhear.R;
import com.example.administrator.lookandhear.UserInfo;
import com.example.administrator.lookandhear.base.BaseFragment;
import com.example.administrator.lookandhear.base.BaseUrl;
import com.example.administrator.lookandhear.base.HotSongBean;
import com.example.administrator.lookandhear.net.HotSongApi;
import com.example.administrator.lookandhear.presenters.HomePagePresenter;
import com.example.administrator.lookandhear.view.HomePageView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by XuYanping on 2017/9/22.
 */

public class HomePageFragment extends BaseFragment<HomePageView, HomePagePresenter> {

    private static final String TAG = "HomePageFragment";

    @Override
    protected HomePagePresenter getPresenter() {
        return new HomePagePresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected String getBaseUrl() {
        return BaseUrl.HOTSONG;
    }

    @Override
    protected void requestDate() {
        super.requestDate();
        mRetrofitManager.creatApi(HotSongApi.class).getHotSongList(UserInfo.USERID, UserInfo.USERKEY, "/song_t.html").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HotSongBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, d.toString());
            }

            @Override
            public void onNext(HotSongBean value) {
                Log.d(TAG, value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
