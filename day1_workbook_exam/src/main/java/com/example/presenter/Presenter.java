package com.example.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.model.LoginModel;
import com.example.model.Model;
import com.example.view.LoginCallBack;
import com.example.view.LoginView;

public class Presenter implements LoginPresenter, LoginCallBack {
    private LoginView loginView;
    private LoginModel loginModel;

    public Presenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new Model();
    }

    @Override
    public void login(String username, String password, Context context) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        loginModel.login(username, password, this);
    }

    @Override
    public void register(String username, String password, String phone, String verify, Context context) {
        loginModel.register(username, password, phone, verify, this, context);
    }

    @Override
    public void loginSuccess(String success) {
        loginView.loginSuccess(success);
    }

    @Override
    public void loginFail(String error) {
        loginView.loginFail(error);
    }

    @Override
    public void registerSuccess(String success) {
        loginView.registerSuccess(success);
    }

    @Override
    public void registerFail(String error) {
        loginView.registerFail(error);
    }
}
