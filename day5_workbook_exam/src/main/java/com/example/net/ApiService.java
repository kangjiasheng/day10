package com.example.net;

import com.example.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl = "http://c.m.163.com/";

    @GET("nc/article/headline/T1348647909107/0-20.html")
    Observable<NewsBean> getNewsData();
}
