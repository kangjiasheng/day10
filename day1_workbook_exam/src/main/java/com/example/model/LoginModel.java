package com.example.model;

import android.content.Context;

import com.example.view.LoginCallBack;

public interface LoginModel {
    void login(String username, String password, LoginCallBack loginCallBack);

    void register(String username, String password, String phone, String verify, LoginCallBack loginCallBack, Context context);
}
