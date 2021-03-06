package com.example.presenter;

import com.example.base.BasePresenter;
import com.example.bean.NewsBean;
import com.example.model.MainModel;
import com.example.net.MainCallBack;
import com.example.view.MainView;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack {
    public MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
        setModel(mainModel);
    }

    @Override
    public void success(NewsBean newsBean) {
        mView.success(newsBean);
    }

    @Override
    public void fail(String error) {
        mView.fail(error);
    }

    public void getData() {
        mainModel.getData(this);
    }
}
