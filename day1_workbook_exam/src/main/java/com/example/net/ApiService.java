package com.example.net;

import com.example.bean.LoginBean;
import com.example.bean.RegisterBean;
import com.example.bean.VerifyBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    String baseUrl = "http://yun918.cn/";

    @POST("study/public/index.php/login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("username") String username, @Field("password") String password);

    @POST("study/public/index.php/register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@Field("username") String username, @Field("password") String password, @Field("phone") String phone, @Field("verify") String verify);

    @GET("study/public/index.php/verify")
    Observable<VerifyBean> verify();
}
