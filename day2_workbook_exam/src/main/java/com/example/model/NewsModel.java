package com.example.model;

import android.content.Context;

import com.example.bean.NewsDbBean;
import com.example.view.NewsCallBack;

public interface NewsModel {
    void getNewsData(NewsCallBack newsCallBack);

    void getCollectionData(NewsCallBack newsCallBack);

    void insert(NewsDbBean newsDbBean, Context context, NewsCallBack newsCallBack);
}
