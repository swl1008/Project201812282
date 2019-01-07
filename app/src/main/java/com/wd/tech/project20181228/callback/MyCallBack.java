package com.wd.tech.project20181228.callback;

public interface MyCallBack<T> {
    void onSuccess(T data);
    void onFail(String error);
}
