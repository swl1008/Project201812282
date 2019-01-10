package com.wd.tech.project20181228.presenter;

import java.util.Map;

public interface Ipresenter {
    //post
    void startRequestPost(String url, Map<String, String> params, Class clazz);
    //get
    void startRequestGet(String url,Class clazz);
    //put
    void startRequestPut(String url, Map<String, String> params, Class clazz);
}
