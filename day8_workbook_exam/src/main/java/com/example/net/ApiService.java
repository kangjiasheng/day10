package com.example.net;

import com.example.bean.DetailsBean;
import com.example.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String baseUrl = "https://api.yunxuekeji.cn/";

    @GET("yunxue_app_api/content/getData/30/66597/1/10")
    Observable<HomeBean> getData();

    String detailsUrl = "https://api.yunxuekeji.cn/";

    @GET("yunxue_app_api/teacher/getTeacherPower/{ID}")
    Observable<DetailsBean> getDetailsData(@Path("ID") int id);
}
