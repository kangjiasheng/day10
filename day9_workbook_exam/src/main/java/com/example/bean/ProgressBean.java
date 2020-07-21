package com.example.bean;

public class ProgressBean {
    private int progress;
    private long contentLength;

    public ProgressBean(int progress, long contentLength) {
        this.progress = progress;
        this.contentLength = contentLength;
    }

    public int getProgress() {
        return progress;
    }

    public long getContentLength() {
        return contentLength;
    }
}
