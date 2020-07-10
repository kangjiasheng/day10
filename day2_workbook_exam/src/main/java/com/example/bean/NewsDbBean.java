package com.example.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class NewsDbBean {
    @Id
    Long db_Id;
    private int news_id;
    private String url;
    private String thumbnail;
    private String title;
    @Generated(hash = 775672762)
    public NewsDbBean(Long db_Id, int news_id, String url, String thumbnail,
            String title) {
        this.db_Id = db_Id;
        this.news_id = news_id;
        this.url = url;
        this.thumbnail = thumbnail;
        this.title = title;
    }
    @Generated(hash = 2049854230)
    public NewsDbBean() {
    }
    public Long getDb_Id() {
        return this.db_Id;
    }
    public void setDb_Id(Long db_Id) {
        this.db_Id = db_Id;
    }
    public int getNews_id() {
        return this.news_id;
    }
    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
