package com.example.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adapter.HomeAdapter;
import com.example.base.BaseFragment;
import com.example.bean.HomeBean;
import com.example.day9_workbook_exam.DownLoadActivity;
import com.example.day9_workbook_exam.R;
import com.example.presenter.MainPresenter;
import com.example.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<MainPresenter> implements MainView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<HomeBean.DataBean.DatasBean> datasBeanArrayList;
    private HomeAdapter adapter;

    @Override
    protected void initListener() {
        adapter.setOnClickItemListener(new HomeAdapter.OnClickItemListener() {
            @Override
            public void clickItem() {
                startActivity(new Intent(getActivity(), DownLoadActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        datasBeanArrayList = new ArrayList<>();
        adapter = new HomeAdapter(datasBeanArrayList, getActivity());
        recycler.setAdapter(adapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    public void fail(String error) {
        Log.e(TAG, "fail: 嘻嘻 出错嘞：" + error);
    }

    private static final String TAG = "HomeFragment";

    @Override
    public void success(HomeBean homeBean) {
        datasBeanArrayList.addAll(homeBean.getData().getDatas());
        adapter.notifyDataSetChanged();
    }
}
