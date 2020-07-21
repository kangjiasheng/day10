package com.example.model;

import android.content.Context;
import android.content.Intent;

import com.example.net.ApiService;
import com.example.bean.LoginBean;
import com.example.bean.RegisterBean;
import com.example.day1_workbook_exam.RegisterActivity;
import com.example.view.LoginCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements LoginModel {
    @Override
    public void login(String username, String password, final LoginCallBack loginCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<LoginBean> login = apiService.login(username, password);

        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        int code = value.getCode();
                        if (code == 200) {
                            loginCallBack.loginSuccess(value.getRet());
                        } else {
                            loginCallBack.loginSuccess(value.getRet());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginCallBack.loginFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void register(final String username, final String password, String phone, String verify, final LoginCallBack loginCallBack, final Context context) {
        final RegisterActivity registerActivity = (RegisterActivity) context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<RegisterBean> register = apiService.register(username, password, phone, verify);

        register.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean value) {
                        if (value.getCode() == 200) {
                            loginCallBack.registerSuccess(value.getRet());
                            Intent intent = new Intent();
                            intent.putExtra("user", username);
                            intent.putExtra("pass", password);
                            registerActivity.setResult(200, intent);
                        } else {
                            loginCallBack.registerSuccess(value.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginCallBack.registerFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
