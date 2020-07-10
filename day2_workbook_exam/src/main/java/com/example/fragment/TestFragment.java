package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.day2_workbook_exam.R;
import com.example.test.ProductB;
import com.example.test.TrueLastFactory;

public class TestFragment extends Fragment implements View.OnClickListener {
    private Button mBtnTest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.test_fragment, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mBtnTest = (Button) inflate.findViewById(R.id.btn_test);

        mBtnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
//                TrueFactory trueFactory = new TrueFactory();
                TrueLastFactory trueLastFactory = new TrueLastFactory();
                ProductB productB = trueLastFactory.testFactory(ProductB.class);
                productB.say();
                break;
        }
    }
}
