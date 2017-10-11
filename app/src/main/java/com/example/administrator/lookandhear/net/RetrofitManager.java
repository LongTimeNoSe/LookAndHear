package com.example.administrator.lookandhear.net;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.lookandhear.BuildConfig;
import com.example.administrator.lookandhear.LHApplication;
import com.example.administrator.lookandhear.utils.NetStateUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XuYanping on 2017/9/20.
 */

public class RetrofitManager {

    public static Retrofit mRetrofit;
    private static RetrofitManager mRetrofitManager;

    public RetrofitManager(String baseUrl) {
        getRetrofit(baseUrl);
    }

    public static synchronized RetrofitManager getInstance(String baseUrl) {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager(baseUrl);
        }
        return mRetrofitManager;
    }

    public static Retrofit getRetrofit(String baseUrl) {

        File httpCacheDirectory = new File(LHApplication.mContext.getCacheDir(), "netCache");
        int cashSize = 1024 * 1024 * 12;
        Cache mCache = new Cache(httpCacheDirectory, cashSize);

        OkHttpClient.Builder client = new OkHttpClient.Builder().cache(mCache).addInterceptor(new MyInterceptor()).connectTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true);


        if (BuildConfig.DEBUG) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    if (TextUtils.isEmpty(message)) return;
                    String s = message.substring(0, 1);
                    //如果收到想响应是json才打印
                    if ("{".equals(s) || "[".equals(s)) {
                        Log.d("postRequest", "收到响应：" + message);
                    }
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);


        }

        mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return mRetrofit;
    }

    public <T> T creatApi(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    static class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            CacheControl.Builder builder = new CacheControl.Builder();
            //这个是控制缓存的最大生命时间   maxAge设置为0，不会取缓存，直接走网络。
            //此处设置缓存时间为两小时（两小时之内读取缓存）
            builder.maxAge(2, TimeUnit.HOURS);
            //缓存有效期一个月
            builder.maxStale(30, TimeUnit.DAYS);

            CacheControl control = builder.build();
            Request request = chain.request();

            //打印发送请求
            if (BuildConfig.DEBUG) {
                Log.d("postRequest", "发送请求：" + request.url() + "-" + chain.connection() + "-" + request.headers());
            }

            if (!NetStateUtil.isNetworkAvailable(LHApplication.mContext)) {
                request = request.newBuilder().cacheControl(control).build();
            }
            Response response = chain.proceed(request);
            if (!NetStateUtil.isNetworkAvailable(LHApplication.mContext)) {

                int maxAge = 0;
                return response.newBuilder().removeHeader("Pragma").header("Cache-Control", "public ,max-age=" + maxAge).build();

            } else {
                int maxStatle = 60 * 60 * 24 * 28;//28天
                return response.newBuilder().removeHeader("Pragma").header("Cache-Control", "public, only-if-cached, max-stale=" + maxStatle).build();
            }
        }
    }
}
