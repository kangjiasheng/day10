package com.example.day1_workbook_exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.net.ApiService;
import com.example.bean.VerifyBean;
import com.example.presenter.Presenter;
import com.example.view.LoginView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private EditText mEtUser;
    private EditText mEtPass;
    private EditText mEtSurePass;
    private EditText mEtPhone;
    private EditText mEtVerify;
    private Button mRegister;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initVerify();
        presenter = new Presenter(this);
    }

    private void initVerify() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<VerifyBean> verify1 = apiService.verify();
        verify1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerifyBean value) {
                        String data = value.getData();
                        Log.e(TAG, "onNext: 验证码：" + data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: 错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtPass = (EditText) findViewById(R.id.et_pass);
        mEtSurePass = (EditText) findViewById(R.id.et_sure_pass);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtVerify = (EditText) findViewById(R.id.et_verify);
        mRegister = (Button) findViewById(R.id.register);

        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        final String user = mEtUser.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        final String pass = mEtPass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String surePass = mEtSurePass.getText().toString().trim();
        if (TextUtils.isEmpty(surePass)) {
            Toast.makeText(this, "确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        final String verify = mEtVerify.getText().toString().trim();
        if (TextUtils.isEmpty(verify)) {
            Toast.makeText(this, "验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!surePass.equals(pass)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        presenter.register(user, pass, phone, verify, this);
        initVerify();
    }

    @Override
    public void loginSuccess(String success) {

    }

    @Override
    public void loginFail(String error) {

    }

    @Override
    public void registerSuccess(String success) {
        Log.e(TAG, "registerSuccess: " + success);
    }

    private static final String TAG = "RegisterActivity";

    @Override
    public void registerFail(String error) {
        Log.e(TAG, "registerFail: 错误信息：" + error);
    }
}
