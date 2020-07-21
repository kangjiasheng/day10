package com.example.net;

import com.example.bean.NewsBean;

public interface MainCallBack {
    void success(NewsBean newsBean);

    void fail(String error);
}
