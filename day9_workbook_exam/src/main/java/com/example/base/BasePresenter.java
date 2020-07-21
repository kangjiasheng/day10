package com.example.base;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public List<BaseModel> baseModels = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void setBaseModels(BaseModel model) {
        baseModels.add(model);
    }

    public void bindView(V view) {
        this.mView = view;
    }

    public void destroy() {
        mView = null;
        for (int i = 0; i < baseModels.size(); i++) {
            baseModels.get(i).disposable();
        }
    }
}
