package com.wd.tech.project20181228.model;

import com.wd.tech.project20181228.callback.MyCallBack;

import java.util.Map;

public interface Imodel {
    //post请求
    void requestDataPost(String url, Map<String, String> params, Class clazz, MyCallBack callBack);
    //get请求
    void requestDataGet(String url,Class clazz,MyCallBack callBack);
    //put请求
    void requestDataPut(String url, Map<String, String> params, Class clazz, MyCallBack callBack);
}
