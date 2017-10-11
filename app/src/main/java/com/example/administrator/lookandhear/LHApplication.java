package com.example.administrator.lookandhear;

import android.app.Application;
import android.content.Context;

/**
 * Created by XuYanping on 2017/9/20.
 */

public class LHApplication extends Application{

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
