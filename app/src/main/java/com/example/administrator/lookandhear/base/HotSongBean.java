package com.example.administrator.lookandhear.base;

import java.util.List;

/**
 * Created by XuYanping on 2017/9/29.
 */

public class HotSongBean {

    public ShowapiResBodyBean showapi_res_body;
    public int showapi_res_code;
    public String showapi_res_error;

    public static class ShowapiResBodyBean {

        public int ret_code;
        public List<MusicListBean> musicList;

        public static class MusicListBean {

            public String link;
            public String title;
            public String word_url;
        }
    }
}
