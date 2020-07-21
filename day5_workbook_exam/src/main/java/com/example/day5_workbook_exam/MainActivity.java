package com.example.day5_workbook_exam;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adapter.NewsAdapter;
import com.example.base.BaseActivity;
import com.example.bean.NewsBean;
import com.example.presenter.MainPresenter;
import com.example.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.set)
    Button set;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.sure)
    Button sure;
    private ArrayList<NewsBean.T1348647909107Bean> t1348647909107BeanArrayList;
    private NewsAdapter adapter;
    private int ItemPosition;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        t1348647909107BeanArrayList = new ArrayList<>();
        adapter = new NewsAdapter(t1348647909107BeanArrayList, this);
        recycler.setAdapter(adapter);

        adapter.setOnClickItemListener(new NewsAdapter.OnClickItemListener() {
            @Override
            public void clickItem(int position) {
                ItemPosition = position;
            }
        });
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
    public void success(NewsBean newsBean) {
        t1348647909107BeanArrayList.addAll(newsBean.getT1348647909107());
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.set, R.id.delete, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set:
                //checkBox已显示直接返回，不执行后面的代码
                if (adapter.isShow == true) {
//                    Toast.makeText(this, "已显示checkBox", Toast.LENGTH_SHORT).show();
                    return;
                }
                //显示checkBox
                adapter.isShow = true;
                adapter.notifyDataSetChanged();
                break;
            case R.id.delete:
                //checkBox未显示直接返回，不执行后面的代码
                if (adapter.isShow == false) {
//                    Toast.makeText(this, "未显示checkBox", Toast.LENGTH_SHORT).show();
                    return;
                }
                //创建新集合，用来存储未勾选checkBox的数据
                ArrayList<NewsBean.T1348647909107Bean> newNewsBeanList = new ArrayList<>();
                //循环数据集合
                for (int i = 0; i < t1348647909107BeanArrayList.size(); i++) {
                    NewsBean.T1348647909107Bean t1348647909107Bean = t1348647909107BeanArrayList.get(i);
                    if (t1348647909107Bean.isCheckState() == false) {
                        //未勾选checkBox的数据，添加到新集合
                        newNewsBeanList.add(t1348647909107Bean);
                    }
                }
                //清空旧集合数据
                t1348647909107BeanArrayList.clear();
                //添加新集合数据
                t1348647909107BeanArrayList.addAll(newNewsBeanList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.sure:
                for (int i = 0; i < t1348647909107BeanArrayList.size(); i++) {
                    NewsBean.T1348647909107Bean t1348647909107Bean = t1348647909107BeanArrayList.get(i);
                    //点击完成，把勾选状态全部设成未勾选
                    t1348647909107Bean.setCheckState(false);
                }
                //隐藏checkBox
                adapter.isShow = false;
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
