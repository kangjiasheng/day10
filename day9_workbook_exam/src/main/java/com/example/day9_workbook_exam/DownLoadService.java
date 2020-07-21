package com.example.day9_workbook_exam;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.bean.ProgressBean;
import com.example.net.ApiService;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class DownLoadService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        download();
        super.onCreate();
    }

    private void download() {
        ApiService apiService = new Retrofit.Builder()
                .baseUrl(ApiService.downLoad)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
        apiService.downLoad()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        long contentLength = responseBody.contentLength();
                        saveFile(inputStream, contentLength, Environment.getExternalStorageDirectory() + "/111.apk");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: 错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void saveFile(InputStream inputStream, long contentLength, String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] bytes = new byte[1024 * 4];
            int length = 0;
            int curr = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
                curr += length;
                EventBus.getDefault().post(new ProgressBean(curr, contentLength));
            }
            inputStream.close();
            fileOutputStream.close();
            notification();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification build = new NotificationCompat.Builder(this, "id")
                .setContentText("下载完成")
                .setContentTitle("完成")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(200, build);
    }
}
