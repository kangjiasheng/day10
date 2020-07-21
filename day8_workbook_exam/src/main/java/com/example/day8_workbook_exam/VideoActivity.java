package com.example.day8_workbook_exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView videoView;
    private String videoUrl = "https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/从技术走向管理/001.从技术到管理_第1节_从技术到管理的内外部因素.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        videoView.setVideoPath(videoUrl);
        videoView.start();
    }
}
