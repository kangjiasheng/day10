package com.example.db;

import com.example.bean.NewsDbBean;
import com.example.greendaodemo.db.DaoSession;
import com.example.greendaodemo.db.NewsDbBeanDao;

import java.util.List;

public class DbUtil {
    public static DaoSession daoSession = MyApp.getDaoSession();

    public static String insert(NewsDbBean newsDbBean) {
        if (!query(newsDbBean)) {
            daoSession.insertOrReplace(newsDbBean);
            return "插入成功";
        }
        return "插入失败";
    }

    public static List<NewsDbBean> queryAll() {
        return daoSession.loadAll(NewsDbBean.class);
    }

    public static boolean query(NewsDbBean newsDbBean) {
        NewsDbBean unique = daoSession.queryBuilder(NewsDbBean.class).where(NewsDbBeanDao.Properties.News_id.eq(newsDbBean.getNews_id())).unique();
        return unique == null ? false : true;
    }
}
