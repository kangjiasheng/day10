package com.example.base;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public List<BaseModel> modelList = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void setModelList(BaseModel baseModel) {
        modelList.add(baseModel);
    }

    public void bindView(V view) {
        this.mView = view;
    }

    public void destroy() {
        mView = null;
        for (int i = 0; i < modelList.size(); i++) {
            modelList.get(i).disposable();
        }
    }
}
