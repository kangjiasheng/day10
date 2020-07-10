package com.example.test;

import android.util.Log;

public class ProductA implements Product {
    @Override
    public void say() {
        Log.e("TAG", "say: ProductA：测试A" );
    }
}
