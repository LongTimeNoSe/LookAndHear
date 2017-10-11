package com.example.administrator.lookandhear.net;


import com.example.administrator.lookandhear.base.HotSongBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by XuYanping on 2017/9/28.
 */

public interface HotSongApi {

    @GET("/928-2")
    Observable<HotSongBean> getHotSongList(@Query("showapi_appid") String userId, @Query("showapi_sign") String userKey, @Query("id") String topId);

}
