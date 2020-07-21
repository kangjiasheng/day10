package com.example.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adapter.HomeAdapter;
import com.example.base.BaseFragment;
import com.example.bean.HomeBean;
import com.example.broadcast.LocalBroadcast;
import com.example.day10_workbook_exam.R;
import com.example.presenter.MainPresenter;
import com.example.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<MainPresenter> implements MainView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<HomeBean.DataBean.DatasBean> datasBeanArrayList;
    private HomeAdapter adapter;
    private LocalBroadcastManager instance;

    @Override
    protected void initListener() {
        adapter.setOnClickItemListener(new HomeAdapter.OnClickItemListener() {
            @Override
            public void clickItem(int position) {
                Intent intent = new Intent("broadcast");
                intent.putExtra("title", datasBeanArrayList.get(position).getTitle());
                instance.sendBroadcast(intent);
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
        adapter = new HomeAdapter(getActivity(), datasBeanArrayList);
        recycler.setAdapter(adapter);

        instance = LocalBroadcastManager.getInstance(getActivity());
        LocalBroadcast localBroadcast = new LocalBroadcast();
        IntentFilter intentFilter = new IntentFilter("broadcast");
        instance.registerReceiver(localBroadcast, intentFilter);
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
