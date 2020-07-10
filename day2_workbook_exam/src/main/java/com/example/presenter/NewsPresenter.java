package com.example.presenter;

import android.content.Context;

import com.example.bean.NewsDbBean;

public interface NewsPresenter {
    void getNewsData();

    void getCollectionData();

    void insert(NewsDbBean newsDbBean, Context context);
}
