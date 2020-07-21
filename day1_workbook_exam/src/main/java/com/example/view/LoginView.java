package com.example.view;

public interface LoginView {
    void loginSuccess(String success);

    void loginFail(String error);

    void registerSuccess(String success);

    void registerFail(String error);
}
