package com.example.view;

import com.example.base.BaseView;
import com.example.bean.NewsBean;

public interface MainView extends BaseView {
    void success(NewsBean newsBean);
}
