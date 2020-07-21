package com.example.presenter;

import android.content.Context;

public interface LoginPresenter {
    void login(String username, String password, Context context);

    void register(String username, String password, String phone, String verify, Context context);
}
