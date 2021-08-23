package com.okhttp.demo.net.okhttp;

public interface INet<T> {
    void onPrepare();

    void onSuccess(T result, String msg);

    void onFailure(String msg);
}