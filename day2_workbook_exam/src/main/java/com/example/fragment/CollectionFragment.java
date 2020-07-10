package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.CollectionAdapter;
import com.example.bean.NewsBean;
import com.example.bean.NewsDbBean;
import com.example.day2_workbook_exam.R;
import com.example.presenter.Presenter;
import com.example.view.NewsView;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment extends Fragment implements NewsView {
    private RecyclerView mRvCollection;
    private ArrayList<NewsDbBean> collectionListData;
    private CollectionAdapter adapter;
    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.collection_fragment, null);
        initView(inflate);
        presenter = new Presenter(this);
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            presenter.getCollectionData();
        } else {
            if (collectionListData != null && collectionListData.size() > 0)
                collectionListData.clear();
        }
    }

    private void initView(View inflate) {
        mRvCollection = (RecyclerView) inflate.findViewById(R.id.rv_collection);
        mRvCollection.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvCollection.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        collectionListData = new ArrayList<>();
        adapter = new CollectionAdapter(collectionListData, getActivity());
        mRvCollection.setAdapter(adapter);
    }

    @Override
    public void success(NewsBean newsBean) {

    }

    @Override
    public void fail(String error) {

    }

    @Override
    public void getCollectionListData(List<NewsDbBean> newsDbBeans) {
        collectionListData.addAll(newsDbBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void insertState(String state) {

    }
}
