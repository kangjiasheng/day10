package com.example.view;

public interface LoginCallBack {
    void loginSuccess(String success);

    void loginFail(String error);

    void registerSuccess(String success);

    void registerFail(String error);
}
