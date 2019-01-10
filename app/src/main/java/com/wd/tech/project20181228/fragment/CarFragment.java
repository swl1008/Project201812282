package com.wd.tech.project20181228.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.adapter.ShowCarAdapter;
import com.wd.tech.project20181228.apis.Apis;
import com.wd.tech.project20181228.bean.ShowCarBean;
import com.wd.tech.project20181228.presenter.PresenterImpl;
import com.wd.tech.project20181228.view.Iview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarFragment extends BaseFragment implements Iview {

    @BindView(R.id.car_recycle)
    RecyclerView car_recycle;
    @BindView(R.id.car_check)
    CheckBox car_check;
    @BindView(R.id.car_total_price)
    TextView car_total_price;
    @BindView(R.id.car_summit)
    TextView car_summit;

    private PresenterImpl presenter;
    private ShowCarAdapter showCarAdapter;
    private List<ShowCarBean.ResultBean> result;

    @Override
    public void initData(View view) {
        ButterKnife.bind(this,view);
        presenter = new PresenterImpl(this);
        initShowCarView();
        getData();
    }

    @Override
    public int getContent() {
        return R.layout.fragment_car;
    }

    public void initShowCarView(){
        car_recycle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        showCarAdapter = new ShowCarAdapter(getActivity());
        car_recycle.setAdapter(showCarAdapter);
        initChecked();
    }

    @OnClick({R.id.car_check, R.id.car_total_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.car_check:
                checkSeller(car_check.isChecked());
                break;
            case R.id.car_summit:
                break;
        }
    }

    private void initChecked() {
        showCarAdapter.listener(new ShowCarAdapter.onCheckListener(){

            @Override
            public void setOnCkecked(List<ShowCarBean.ResultBean> mList) {
                double price = 0;
                boolean flag = true;

                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isCheck()) {
                        price = price + (mList.get(i).getCount() * mList.get(i).getPrice());
                    } else if (mList.get(i).isCheck() == false) {
                        flag = false;
                    }
                }
                if (flag == false) {
                    car_check.setChecked(false);
                } else if (flag == true) {
                    car_check.setChecked(true);
                }
                car_total_price.setText("总价为：￥" + price);
            }
        });
    }
    private void checkSeller(boolean checked) {

        double price = 0;

        for (int i = 0; i <result.size() ; i++) {
            ShowCarBean.ResultBean resultBean = result.get(i);
            resultBean.isCheck();
            price = price +(result.get(i).getCount()*result.get(i).getPrice());
        }

        showCarAdapter.setChecked(checked);

        if (checked) {
            car_total_price.setText("总价为：￥" + price);
        } else{
            car_total_price.setText("总价为：￥ 0. 00");
        }

    }
    @Override
    public void showDataSuccess(Object data) {
        if (data instanceof ShowCarBean){
            ShowCarBean showCarBean = (ShowCarBean) data;
            result = showCarBean.getResult();
            showCarAdapter.setData(result);
        }
    }

    public void getData(){
        presenter.startRequestGet(Apis.URL_SHOW_CAR,ShowCarBean.class);
    }

    @Override
    public void showDataFail(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
