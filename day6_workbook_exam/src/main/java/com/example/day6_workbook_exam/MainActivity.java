package com.example.day6_workbook_exam;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.adapter.HomeAdapter;
import com.example.base.BaseActivity;
import com.example.bean.HomeBean;
import com.example.presenter.MainPresenter;
import com.example.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {


    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<HomeBean.T1348654151579Bean> t1348654151579BeanArrayList;
    private HomeAdapter adapter;

    @Override
    protected void initListener() {
        adapter.setOnClickItemListener(new HomeAdapter.OnClickItemListener() {
            @Override
            public void clickItem(int position) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class)
                        .putExtra("url",
                                t1348654151579BeanArrayList.get(position)
                                        .getUrl())
                        .putExtra("title",
                                t1348654151579BeanArrayList.get(position)
                                        .getTitle()));
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        t1348654151579BeanArrayList = new ArrayList<>();
        adapter = new HomeAdapter(t1348654151579BeanArrayList, this);

        recycler.setAdapter(adapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void fail(String error) {
        Log.e(TAG, "fail: 错误信息：" + error);
    }

    private static final String TAG = "MainActivity";

    @Override
    public void success(HomeBean homeBean) {
//        textTitle.setText(homeBean.getT1348654151579().get(0).getSource());
        t1348654151579BeanArrayList.addAll(homeBean.getT1348654151579());
        adapter.notifyDataSetChanged();
    }
}
