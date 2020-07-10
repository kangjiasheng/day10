package com.example.test;

import android.util.Log;

public class ProductB implements Product {
    @Override
    public void say() {
        Log.e("TAG", "say: ProductB：测试B");
    }
}
