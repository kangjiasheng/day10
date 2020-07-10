package com.example.test;

public class TrueLastFactory implements LastFactory {
    @Override
    public <T extends Product> T testFactory(Class<T> tClass) {
        Product product = null;

        try {
            product = (Product) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) product;
    }
}
