package com.example.net;

import com.example.bean.HomeBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface ApiService {
    String baseUrl = "https://www.wanandroid.com/";
    String downLoad = "http://yun918.cn/";

    @GET("project/list/1/json?cid=294")
    Observable<HomeBean> getData();

    @GET("study/public/res/UnknowApp-1.0.apk")
    @Streaming
    Observable<ResponseBody> downLoad();
}
