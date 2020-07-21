package com.example.net;

import com.example.bean.HomeBean;

public interface MainCallBack {
    void success(HomeBean homeBean);

    void fail(String error);
}
