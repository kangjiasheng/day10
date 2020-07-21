package com.example.day6_workbook_exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;
    final String APP_CACHE_DIRNAME = "/webViewCache"; // WebView缓存目录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        
        initView();
    }

    private void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.e("111111111111111", "initView: " + getIntent().getStringExtra("url"));
        toolbarTitle.setText(getIntent().getStringExtra("title"));
        webView.loadUrl(getIntent().getStringExtra("url"));
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        String cachePath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        //设置数据库缓存路径
        settings.setAppCachePath(cachePath);
        settings.setAppCacheEnabled(true);
        webView.reload();
    }
}
