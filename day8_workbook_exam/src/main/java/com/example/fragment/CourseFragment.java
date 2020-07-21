package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.CourseAdapter;
import com.example.bean.HomeBean;
import com.example.day8_workbook_exam.R;
import com.example.day8_workbook_exam.VideoActivity;
import com.example.net.ApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private ArrayList<HomeBean.BodyBean.ResultBean> resultBeans;
    private CourseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.course_fragment, null);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initData();
        return inflate;
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        resultBeans = new ArrayList<>();
        adapter = new CourseAdapter(resultBeans, getActivity());
        recycler.setAdapter(adapter);

        adapter.setOnClickItemListener(new CourseAdapter.OnClickItemListener() {
            @Override
            public void clickItem() {
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        List<HomeBean.BodyBean.ResultBean> result = homeBean.getBody().getResult();
                        Log.e(TAG, "onNext: 111111111111" + result.get(0).getDescription());
                        resultBeans.addAll(result);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static final String TAG = "CourseFragment";

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
