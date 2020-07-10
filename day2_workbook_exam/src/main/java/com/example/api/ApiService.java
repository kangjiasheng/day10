package com.example.api;

import com.example.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl = "http://news-at.zhihu.com/";

    @GET("api/4/news/hot")
    Observable<NewsBean> getNewsData();
}
