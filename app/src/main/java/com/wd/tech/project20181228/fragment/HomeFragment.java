package com.wd.tech.project20181228.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.activity.DetailsActivity;
import com.wd.tech.project20181228.adapter.HomeSearchAdapter;
import com.wd.tech.project20181228.adapter.HotProductAdapter;
import com.wd.tech.project20181228.adapter.MagicFashionAdapter;
import com.wd.tech.project20181228.adapter.QualityLifeAdapter;
import com.wd.tech.project20181228.apis.Apis;
import com.wd.tech.project20181228.bean.HomeBannerBean;
import com.wd.tech.project20181228.bean.HomeBean;
import com.wd.tech.project20181228.bean.HomeSearchBean;
import com.wd.tech.project20181228.presenter.PresenterImpl;
import com.wd.tech.project20181228.view.Iview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements Iview {

    //图片
    @BindView(R.id.image_home_search)
    ImageView image_home_search;
    @BindView(R.id.image_home_back)
    ImageView image_home_back;
    @BindView(R.id.image_home_sort)
    ImageView image_home_sort;
    //输入框
    @BindView(R.id.home_search_edit)
    EditText home_edit_search;
    //文本
    @BindView(R.id.text_home_search)
    TextView text_home_search;
    //滚动Scroll
    @BindView(R.id.home_srcollview)
    ScrollView home_scrollview;
    //搜索无结果的布局
    @BindView(R.id.home_linear)
    LinearLayout home_linear;
    //RecycleView
    @BindView(R.id.home_recycle_rxxp)
    RecyclerView home_recycle_rxxp;
    @BindView(R.id.home_recycle_mlss)
    RecyclerView home_recycle_mlss;
    @BindView(R.id.home_recycle_pzsh)
    RecyclerView home_recycle_pzsh;
    @BindView(R.id.home_recycle_search)
    RecyclerView home_recycle_search;
    //轮播图
    @BindView(R.id.home_banner)
    XBanner home_xbanner;
    private PresenterImpl presenter;
    //首页展示适配器
    //热销新品
    private HotProductAdapter hotProductAdapter;
    //魔力时尚
    private MagicFashionAdapter magicFashionAdapter;
    //品质生活
    private QualityLifeAdapter qualityLifeAdapter;
    //搜索适配器
    private HomeSearchAdapter homeSearchAdapter;
    //轮播图适配器
    private XBanner.XBannerAdapter xBannerAdapter;

    @Override
    public void initData(View view) {
        ButterKnife.bind(this,view);
        presenter = new PresenterImpl(this);
        initData();
        //热销新品
        initHotProductView();
        //魔力时尚
        initMagicFashionView();
        //品质生活
        initQualityLifeView();
        //搜索
        initHomeSearchView();
    }

    @Override
    public int getContent() {
        return R.layout.fragment_home;
    }

    //放大镜搜索的点击事件
    @OnClick(R.id.image_home_search)
    public void onImageSearchClickListener(){
        home_edit_search.setVisibility(View.VISIBLE);
        text_home_search.setVisibility(View.VISIBLE);
        image_home_search.setVisibility(View.GONE);
    }

    //文本框搜索的点击事件
    @OnClick(R.id.text_home_search)
    public void onTextSearchClickListener(){
        String edit_name = home_edit_search.getText().toString().trim();
        if (edit_name.equals("")){
            home_edit_search.setVisibility(View.GONE);
            text_home_search.setVisibility(View.GONE);
            image_home_search.setVisibility(View.VISIBLE);
        }else {
            if (edit_name.equals("")){
                home_scrollview.setVisibility(View.VISIBLE);
                image_home_sort.setVisibility(View.VISIBLE);
                home_recycle_search.setVisibility(View.GONE);
                image_home_back.setVisibility(View.GONE);
                home_edit_search.setText("");
            }else{
                home_scrollview.setVisibility(View.GONE);
                image_home_sort.setVisibility(View.GONE);
                home_recycle_search.setVisibility(View.VISIBLE);
                image_home_back.setVisibility(View.VISIBLE);
                presenter.startRequestGet(String.format(Apis.URL_HOME_SEARCH,edit_name),HomeSearchBean.class);
            }

        }
    }
    @OnClick(R.id.image_home_back)
    public void onBackSearchClickListener(){
        home_scrollview.setVisibility(View.VISIBLE);
        image_home_sort.setVisibility(View.VISIBLE);
        home_recycle_search.setVisibility(View.GONE);
        image_home_back.setVisibility(View.GONE);
        home_edit_search.setText("");
    }

    //热销新品
    public void initHotProductView(){
        home_recycle_rxxp.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        hotProductAdapter = new HotProductAdapter(getActivity());
        home_recycle_rxxp.setAdapter(hotProductAdapter);
        hotProductAdapter.setOnItemClickListener(new HomeSearchAdapter.onItemClickListener() {
            @Override
            public void onClick(int commodityId) {
                EventBus.getDefault().postSticky(commodityId);
                startActivity(new Intent(getActivity(),DetailsActivity.class));
            }
        });
    }
    //魔力时尚
    public void initMagicFashionView(){
        home_recycle_mlss.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        magicFashionAdapter = new MagicFashionAdapter(getActivity());
        home_recycle_mlss.setAdapter(magicFashionAdapter);
        magicFashionAdapter.setOnItemClickListener(new HomeSearchAdapter.onItemClickListener() {
            @Override
            public void onClick(int commodityId) {
                EventBus.getDefault().postSticky(commodityId);
                startActivity(new Intent(getActivity(),DetailsActivity.class));
            }
        });
    }
    //品质生活
    public void initQualityLifeView(){
        home_recycle_pzsh.setLayoutManager(new GridLayoutManager(getActivity(),2));
        qualityLifeAdapter = new QualityLifeAdapter(getActivity());
        home_recycle_pzsh.setAdapter(qualityLifeAdapter);
        qualityLifeAdapter.setOnItemClickListener(new HomeSearchAdapter.onItemClickListener() {
            @Override
            public void onClick(int commodityId) {
                EventBus.getDefault().postSticky(commodityId);
                startActivity(new Intent(getActivity(),DetailsActivity.class));
            }
        });
    }
    //搜索
    public void initHomeSearchView(){
        home_recycle_search.setLayoutManager(new GridLayoutManager(getActivity(),2));
        homeSearchAdapter = new HomeSearchAdapter(getActivity());
        home_recycle_search.setAdapter(homeSearchAdapter);
        homeSearchAdapter.setOnItemClickListener(new HomeSearchAdapter.onItemClickListener() {
            @Override
            public void onClick(int commodityId) {
                EventBus.getDefault().postSticky(commodityId);
                startActivity(new Intent(getActivity(),DetailsActivity.class));
            }
        });


    }

    @Override
    public void showDataSuccess(Object data) {
            if(data instanceof HomeBean){
                //热销新品的数据
                HomeBean homeBean = (HomeBean) data;
                List<HomeBean.ResultBean.RxxpBean> rxxp = homeBean.getResult().getRxxp();
                List<HomeBean.ResultBean.RxxpBean.CommodityListBean> hot = rxxp.get(0).getCommodityList();
                hotProductAdapter.setData(hot);
                //魔力时尚的数据
                List<HomeBean.ResultBean.MlssBean> mlss = homeBean.getResult().getMlss();
                List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> magic = mlss.get(0).getCommodityList();
                magicFashionAdapter.setData(magic);
                //品质生活的数据
                List<HomeBean.ResultBean.PzshBean> pzsh = homeBean.getResult().getPzsh();
                List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> quality = pzsh.get(0).getCommodityList();
                qualityLifeAdapter.setData(quality);
            }else if(data instanceof HomeSearchBean){
                HomeSearchBean homeSearchBean = (HomeSearchBean) data;
                List<HomeSearchBean.ResultBean> result = homeSearchBean.getResult();
                if (result.size()==0){
                    home_linear.setVisibility(View.VISIBLE);
                    home_recycle_search.setVisibility(View.GONE);
                }else{
                    homeSearchAdapter.setData(homeSearchBean.getResult());
                }

            }else if (data instanceof HomeBannerBean){
                HomeBannerBean bannerBean = (HomeBannerBean) data;
                final List<HomeBannerBean.ResultBean> banner1 = bannerBean.getResult();
                ArrayList<String> mlist = new ArrayList<>();
                for (int i = 0; i < banner1.size(); i++) {
                    mlist.add(banner1.get(i).getImageUrl());
                }
                home_xbanner.setData(banner1, null);
                xBannerAdapter = new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        //设置图片圆角角度
                        RoundedCorners roundedCorners = new RoundedCorners(10);
                        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 150);
                        Glide.with(getActivity()).load(banner1.get(position).getImageUrl()).apply(options).into((ImageView) view);
                    }
                };
                home_xbanner.loadImage(xBannerAdapter);
                home_xbanner.setPageTransformer(Transformer.Default);
                home_xbanner.setPageTransformer(Transformer.Alpha);
                home_xbanner.setPageTransformer(Transformer.ZoomFade);
                home_xbanner.setPageTransformer(Transformer.ZoomCenter);
                home_xbanner.setPageTransformer(Transformer.ZoomStack);
                home_xbanner.setPageTransformer(Transformer.Stack);
                home_xbanner.setPageTransformer(Transformer.Depth);
                home_xbanner.setPageTransformer(Transformer.Zoom);
                home_xbanner.setPageChangeDuration(1);

            }

    }

    @Override
    public void showDataFail(String error) {

    }

    public void initData(){
        presenter.startRequestGet(Apis.URL_SHOW_PRODUCTS,HomeBean.class);
        presenter.startRequestGet(Apis.URL_HOME_BANNER,HomeBannerBean.class);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.onDetach();
        }

    }
}
