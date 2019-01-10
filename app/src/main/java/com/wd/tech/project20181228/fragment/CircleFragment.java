package com.wd.tech.project20181228.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.adapter.CircleAdapter;
import com.wd.tech.project20181228.apis.Apis;
import com.wd.tech.project20181228.bean.CircleBean;
import com.wd.tech.project20181228.presenter.PresenterImpl;
import com.wd.tech.project20181228.view.Iview;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleFragment extends BaseFragment implements Iview {


    @BindView(R.id.circle_recycle)
    RecyclerView recyclerView_circle;
    private CircleAdapter circleAdapter;
    private PresenterImpl presenter;

    @Override
    public void initData(View view) {
        ButterKnife.bind(this,view);
        presenter = new PresenterImpl(this);
        initCircleView(view);
        getCircleData();
    }

    @Override
    public int getContent() {
        return R.layout.fragment_circle;
    }

    public void initCircleView(View view){
        recyclerView_circle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        circleAdapter = new CircleAdapter(getActivity());
        recyclerView_circle.setAdapter(circleAdapter);
    }

    @Override
    public void showDataSuccess(Object data) {
        if (data instanceof CircleBean){
            CircleBean circleBean = (CircleBean) data;
            circleAdapter.setData(circleBean.getResult());
        }
    }

    @Override
    public void showDataFail(String error) {

    }

    public void getCircleData(){
        presenter.startRequestGet(Apis.URL_CIRCLE,CircleBean.class);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.onDetach();
        }

    }
}
