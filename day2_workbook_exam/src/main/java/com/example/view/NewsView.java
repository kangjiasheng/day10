package com.example.view;

import com.example.bean.NewsBean;
import com.example.bean.NewsDbBean;

import java.util.List;

public interface NewsView {
    void success(NewsBean newsBean);

    void fail(String error);

    void getCollectionListData(List<NewsDbBean> newsDbBeans);

    void insertState(String state);

}
