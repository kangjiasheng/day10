package com.example.model;

import com.example.base.BaseModel;
import com.example.bean.HomeBean;
import com.example.net.ApiService;
import com.example.net.MainCallBack;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    public void getData(final MainCallBack mainCallBack) {
        new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setCompositeDisposable(d);
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        mainCallBack.success(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainCallBack.fail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
