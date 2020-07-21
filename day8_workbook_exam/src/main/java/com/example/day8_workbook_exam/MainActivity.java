package com.example.day8_workbook_exam;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.adapter.HomeAdapter;
import com.example.base.BaseActivity;
import com.example.bean.HomeBean;
import com.example.presenter.MainPresenter;
import com.example.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<HomeBean.BodyBean.ResultBean> resultBeanArrayList;
    private HomeAdapter adapter;

    @Override
    protected void initListener() {
        adapter.setOnClickItemListener(new HomeAdapter.OnClickItemListener() {
            @Override
            public void clickItem(int position) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("id", resultBeanArrayList.get(position).getID());
                intent.putExtra("list", resultBeanArrayList);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        resultBeanArrayList = new ArrayList<>();
        adapter = new HomeAdapter(resultBeanArrayList, this);
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
    public void success(HomeBean homeBean) {
        resultBeanArrayList.addAll(homeBean.getBody().getResult());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String error) {
        Log.e(TAG, "fail: 错误信息：" + error);
    }

    private static final String TAG = "MainActivity";

}
