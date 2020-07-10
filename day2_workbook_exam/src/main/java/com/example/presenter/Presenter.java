package com.example.presenter;

import android.content.Context;

import com.example.bean.NewsBean;
import com.example.bean.NewsDbBean;
import com.example.model.Model;
import com.example.model.NewsModel;
import com.example.view.NewsCallBack;
import com.example.view.NewsView;

import java.util.List;

public class Presenter implements NewsPresenter {
    private NewsView newsView;
    private NewsModel newsModel;

    public Presenter(NewsView newsView) {
        this.newsView = newsView;
        newsModel = new Model();
    }

    @Override
    public void getNewsData() {
        newsModel.getNewsData(new NewsCallBack() {
            @Override
            public void success(NewsBean newsBean) {
                newsView.success(newsBean);
            }

            @Override
            public void fail(String error) {
                newsView.fail(error);
            }

            @Override
            public void getCollectionListData(List<NewsDbBean> newsDbBeans) {

            }

            @Override
            public void insertState(String state) {

            }
        });
    }

    @Override
    public void getCollectionData() {
        newsModel.getCollectionData(new NewsCallBack() {
            @Override
            public void success(NewsBean newsBean) {

            }

            @Override
            public void fail(String error) {

            }

            @Override
            public void getCollectionListData(List<NewsDbBean> newsDbBeans) {
                newsView.getCollectionListData(newsDbBeans);
            }

            @Override
            public void insertState(String state) {

            }
        });
    }

    @Override
    public void insert(NewsDbBean newsDbBean, Context context) {
        newsModel.insert(newsDbBean, context, new NewsCallBack() {
            @Override
            public void success(NewsBean newsBean) {

            }

            @Override
            public void fail(String error) {

            }

            @Override
            public void getCollectionListData(List<NewsDbBean> newsDbBeans) {

            }

            @Override
            public void insertState(String state) {
                newsView.insertState(state);
            }
        });
    }
}
