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
import android.widget.Toast;

import com.example.adapter.NewsAdapter;
import com.example.bean.NewsBean;
import com.example.bean.NewsDbBean;
import com.example.day2_workbook_exam.DetailsActivity;
import com.example.day2_workbook_exam.R;
import com.example.presenter.Presenter;
import com.example.view.NewsView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements NewsView {
    private RecyclerView mRecycler;
    private ArrayList<NewsDbBean> newsDbBeanList;
    private NewsAdapter adapter;
    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.news_fragment, null);
        initView(inflate);
        presenter = new Presenter(this);
        presenter.getNewsData();
        return inflate;
    }

    private void initView(View inflate) {
        mRecycler = (RecyclerView) inflate.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        newsDbBeanList = new ArrayList<>();
        adapter = new NewsAdapter(newsDbBeanList, getActivity());
        mRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("url", newsDbBeanList.get(position).getUrl());
                startActivity(intent);
            }

            @Override
            public void longItemClick(int position) {
                presenter.insert(newsDbBeanList.get(position), getActivity());
            }
        });
    }

    @Override
    public void success(NewsBean newsBean) {
        newsDbBeanList.addAll(newsBean.getRecent());
        adapter.notifyDataSetChanged();
    }

    private static final String TAG = "NewsFragment";

    @Override
    public void fail(String error) {
        Log.e(TAG, "fail: 错误信息：" + error);
    }

    @Override
    public void getCollectionListData(List<NewsDbBean> newsDbBeans) {

    }

    @Override
    public void insertState(String state) {
        Toast.makeText(getActivity(), state, Toast.LENGTH_SHORT).show();
    }
}
