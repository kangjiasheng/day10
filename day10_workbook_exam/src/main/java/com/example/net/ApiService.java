package com.example.net;

import com.example.bean.HomeBean;
import com.example.bean.UploadBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    String baseUrl = "https://www.wanandroid.com/";

    @GET("project/list/1/json?cid=294")
    Observable<HomeBean> getData();

    String mBaseUrl = "http://yun918.cn/";

    @Multipart
    @POST("study/public/file_upload.php")
    Observable<UploadBean> upload(@Part("key") RequestBody key, @Part MultipartBody.Part file);
}
