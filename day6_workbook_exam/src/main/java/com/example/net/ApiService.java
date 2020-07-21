package com.example.net;

import com.example.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl = "http://c.m.163.com/";

    @GET("nc/article/list/T1348654151579/0-20.html")
    Observable<HomeBean> getData();
}
