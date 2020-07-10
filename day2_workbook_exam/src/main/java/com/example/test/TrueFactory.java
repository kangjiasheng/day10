package com.example.test;

public class TrueFactory implements Factory {
    @Override
    public Product productA() {
        return new ProductA();
    }

    @Override
    public Product productB() {
        return new ProductB();
    }
}
