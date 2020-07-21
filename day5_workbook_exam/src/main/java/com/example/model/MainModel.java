package com.example.model;

import com.example.base.BaseModel;
import com.example.bean.NewsBean;
import com.example.net.ApiService;
import com.example.net.MainCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    public void getData(final MainCallBack mainCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<NewsBean> newsData = apiService.getNewsData();

        newsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setCompositeDisposable(d);
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        mainCallBack.success(newsBean);
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
