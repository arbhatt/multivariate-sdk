package com.ab.multivariate.sdk;

public class Main {
    public static void main(String[] args) {
        System.setProperty("multivariate.sdk.env", "impl");
        MultivariateSdkBuilder.loadApplicationContext();

    }
}
