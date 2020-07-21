package com.example.day9_workbook_exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bean.ProgressBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownLoadActivity extends AppCompatActivity {

    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.start_download)
    Button startDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getProgress(ProgressBean progressBean) {
        long contentLength = progressBean.getContentLength();
        int getProgress = progressBean.getProgress();
        progress.setMax((int) contentLength);
        progress.setProgress(getProgress);
        int v = (int) (100f * getProgress / contentLength);
        tvProgress.setText("当前下载进度：" + v + "%");
    }

    @OnClick(R.id.start_download)
    public void onViewClicked() {
        startService(new Intent(this, DownLoadService.class));
    }
}
