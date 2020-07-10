package com.example.bean;

import java.util.List;

public class NewsBean {

    private List<NewsDbBean> recent;

    public List<NewsDbBean> getRecent() {
        return recent;
    }

    public void setRecent(List<NewsDbBean> recent) {
        this.recent = recent;
    }

}
