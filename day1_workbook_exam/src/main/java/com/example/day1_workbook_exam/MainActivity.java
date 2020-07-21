package com.example.day1_workbook_exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.presenter.Presenter;
import com.example.view.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mLogin;
    private Button mRegister;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new Presenter(this);
    }

    private void initView() {
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mLogin = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);

        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 & resultCode == 300) {
            String user = data.getStringExtra("user");
            String pass = data.getStringExtra("pass");
            mEtUsername.setText(user);
            mEtPassword.setText(pass);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                submit();
                break;
            case R.id.register:
                startActivityForResult(new Intent(this, RegisterActivity.class), 300);
                break;
        }
    }

    private void submit() {
        String username = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        presenter.login(username, password, this);
    }

    private static final String TAG = "MainActivity";

    @Override
    public void loginSuccess(String success) {
        Log.e(TAG, "loginSuccess: " + success);
    }

    @Override
    public void loginFail(String error) {
        Log.e(TAG, "loginFail: 错误信息：" + error);
    }

    @Override
    public void registerSuccess(String success) {

    }

    @Override
    public void registerFail(String error) {

    }
}
