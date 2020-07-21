package com.example.day10_workbook_exam;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.bean.UploadBean;
import com.example.net.ApiService;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadActivity extends AppCompatActivity {

    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_upload)
    public void onViewClicked() {
        File file = new File(Environment.getExternalStorageDirectory() + "/0.jpg");

        //1.创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //2.创建service对象
        ApiService myService = retrofit.create(ApiService.class);
        //3.service对象调用方法
        //封装类型
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        //文件封装
        MultipartBody.Part file1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        //文本封装
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), "H1810b");

        Observable<UploadBean> up = myService.upload(requestBody1, file1);
        up.subscribeOn(Schedulers.io())//子线程请求
                .observeOn(AndroidSchedulers.mainThread())//主线程操作
                .subscribe(new Observer<UploadBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UploadBean uploadBean) {
                        if (uploadBean != null && uploadBean.getCode() == 200) {
                            tvResult.setText(uploadBean.getData().getFilename() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvResult.setText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static final String TAG = "UploadActivity";
}
