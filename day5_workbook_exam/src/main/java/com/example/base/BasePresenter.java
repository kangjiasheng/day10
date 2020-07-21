package com.example.base;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public List<BaseModel> mModel = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void setModel(BaseModel baseModel) {
        mModel.add(baseModel);
    }

    public void bindView(V view) {
        this.mView = view;
    }

    public void destroy() {
        mView = null;
        for (int i = 0; i < mModel.size(); i++) {
            mModel.get(i).disposable();
        }
    }
}
