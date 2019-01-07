package com.wd.tech.project20181228.model;

import android.util.Log;

import com.google.gson.Gson;
import com.wd.tech.project20181228.callback.MyCallBack;
import com.wd.tech.project20181228.network.RetrofitManager;

import java.util.Map;

import okhttp3.RequestBody;

public class ModelImpl<T> implements Imodel {
    //post请求
    @Override
    public void requestDataPost(String url, Map<String, String> params, final Class clazz, final MyCallBack callBack) {
        RetrofitManager.getInstance().post(url,params,new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {

                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (callBack != null){
                        callBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("swl",e+"1");
                    if (callBack != null){
                        callBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                Log.d("swl",error);
                if (callBack != null){
                    callBack.onFail(error);
                }
            }
        });
    }
    //get请求
    @Override
    public void requestDataGet(String url, final Class clazz, final MyCallBack callBack) {
            RetrofitManager.getInstance().get(url,new RetrofitManager.HttpListener() {
                @Override
                public void onSuccess(String data) {
                    try {
                        Object o = new Gson().fromJson(data, clazz);
                        if (callBack != null){
                            callBack.onSuccess(o);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.d("swl",e+"1");
                        if (callBack != null){
                            callBack.onFail(e.getMessage());
                        }
                    }
                }

                @Override
                public void onFail(String error) {
                    Log.d("swl",error);
                    if (callBack != null){
                        callBack.onFail(error);
                    }
                }
            });
    }

}
