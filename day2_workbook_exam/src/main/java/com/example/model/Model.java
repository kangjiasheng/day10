package com.example.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.api.ApiService;
import com.example.bean.NewsBean;
import com.example.bean.NewsDbBean;
import com.example.day2_workbook_exam.R;
import com.example.db.DbUtil;
import com.example.view.NewsCallBack;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements NewsModel {
    @Override
    public void getNewsData(final NewsCallBack newsCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<NewsBean> newsData = apiService.getNewsData();

        newsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean value) {
                        newsCallBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        newsCallBack.fail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCollectionData(NewsCallBack newsCallBack) {
        List<NewsDbBean> newsDbBeans = DbUtil.queryAll();
        newsCallBack.getCollectionListData(newsDbBeans);
    }

    @Override
    public void insert(final NewsDbBean newsDbBean, Context context, final NewsCallBack newsCallBack) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop, null);

        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(inflate, 200, 200);

        Button btn_sure = inflate.findViewById(R.id.sure);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insert = DbUtil.insert(newsDbBean);
                newsCallBack.insertState(insert);
                popupWindow.dismiss();
            }
        });

        Button btn_cancel = inflate.findViewById(R.id.cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
