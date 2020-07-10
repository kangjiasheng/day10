package com.example.test;

public interface LastFactory {
    <T extends Product> T testFactory(Class<T> tClass);
}
