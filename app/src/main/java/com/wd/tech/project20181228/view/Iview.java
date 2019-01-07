package com.wd.tech.project20181228.view;

public interface Iview<T> {
    void showDataSuccess(T data);
    void showDataFail(String error);
}
