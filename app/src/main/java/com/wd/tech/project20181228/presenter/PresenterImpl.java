package com.wd.tech.project20181228.presenter;

import android.util.Log;

import com.wd.tech.project20181228.callback.MyCallBack;
import com.wd.tech.project20181228.model.ModelImpl;
import com.wd.tech.project20181228.view.Iview;

import java.util.Map;

public class PresenterImpl implements Ipresenter{
    private Iview iview;
    private ModelImpl model;

    public PresenterImpl(Iview iview) {
        this.iview = iview;
        model = new ModelImpl();
    }
    @Override
    public void startRequestPost(String url, Map<String, String> params, Class clazz) {
        model.requestDataPost(url, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.showDataSuccess(data);

            }

            @Override
            public void onFail(String error) {
                iview.showDataFail(error);
            }
        });
    }

    @Override
    public void startRequestGet(String url, Class clazz) {
        model.requestDataGet(url, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.showDataSuccess(data);
            }

            @Override
            public void onFail(String error) {
                iview.showDataFail(error);
            }
        });
    }

    public void onDetach(){
        if (iview != null){
            iview = null;
        }
        if (model != null){
            model = null;
        }
    }
}
