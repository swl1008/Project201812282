package com.wd.tech.project20181228.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.adapter.ProductDetailsBannerAdapter;
import com.wd.tech.project20181228.apis.Apis;
import com.wd.tech.project20181228.bean.ProductDetailsBean;
import com.wd.tech.project20181228.custom.ProductDetailsView;
import com.wd.tech.project20181228.presenter.PresenterImpl;
import com.wd.tech.project20181228.view.Iview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements Iview {



    @BindView(R.id.details_productdetailsview)
    ProductDetailsView details_detailScrollView;

    @BindView(R.id.details_viewpager)
    ViewPager details_viewPager;

    @BindView(R.id.details_tv_showNum)
    TextView details_text_showNum;

    @BindView(R.id.details_tv_price)
    TextView details_text_price;

    @BindView(R.id.details_tv_sold)
    TextView details_text_sold;

    @BindView(R.id.details_tv_shopName)
    TextView details_text_shopName;

    @BindView(R.id.details_tv_Weight)
    TextView details_text_weight;

    @BindView(R.id.details_Shop_Image)
    SimpleDraweeView details_simpleDraweeView_img;

    @BindView(R.id.details_tv_describe)  //商品介绍
    TextView details_text_describe;

    @BindView(R.id.details_Image_describe)
    SimpleDraweeView details_simpleDraweeView_describe;

    @BindView(R.id.details_rv_comments)  //评论
    RecyclerView details_recyclerView_comments;

    @BindView(R.id.details_tv_comments)
    TextView details_text_comments;

    @BindView(R.id.details_image_return)
    ImageView details_image_return;

    @BindView(R.id.details_relative_changer)
    RelativeLayout details_relative_changer;

    @BindView(R.id.details_relat_changecolor)
    RelativeLayout details_relativeLayout_relat;

    @BindView(R.id.details_text_goodsT)
    TextView details_text_goods;

    @BindView(R.id.details_text_detailsT)
    TextView details_text_details;

    @BindView(R.id.details_text_commentsT)
    TextView details_text_comment;

    @BindView(R.id.details_text_goods)
    TextView details_text_good;

    @BindView(R.id.details_text_details)
    TextView details_text_detail;

    @BindView(R.id.details_text_comments)
    TextView details_text_commen;

    @BindView(R.id.details_relative_addShoppingCar)
    RelativeLayout details_relativeLayout_add;

    @BindView(R.id.details_relative_pay)
    RelativeLayout details_relativeLayout_pay;
    private PresenterImpl presenter;
    private int mCount;
    int id;
    private String mSessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        presenter = new PresenterImpl(this);
        SharedPreferences sharedPreferences = getSharedPreferences("swl",MODE_PRIVATE);
        mSessionId = sharedPreferences.getString("sessionId",null);
        getData();
        setListener();
    }
    @OnClick(R.id.details_image_return)  //点击返回
    public void onBackClickListener(){
        finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void message(Integer mId){
        id= mId;
    }

    public void getData(){
        presenter.startRequestGet(String.format(Apis.URL_PRODUCT_DETAILS,id),ProductDetailsBean.class);
    }
    @Override
    public void showDataSuccess(Object data) {
            if (data instanceof ProductDetailsBean){
                ProductDetailsBean productDetailsBean = (ProductDetailsBean) data;
                ProductDetailsBean.ResultBean result = productDetailsBean.getResult();
                details_text_price.setText("￥" + result.getPrice());
                details_text_sold.setText("已售" + result.getSaleNum() + "件");
                details_text_shopName.setText(result.getCommodityName());
                details_text_weight.setText(result.getWeight() + "kg");
                details_text_describe.setText(result.getDescribe());

                String picture = result.getPicture();
                String[] split = picture.split(",");

                details_simpleDraweeView_img.setImageURI(split[0]);
                details_simpleDraweeView_describe.setImageURI(split[1]);

                ProductDetailsBannerAdapter bannerAdapter = new ProductDetailsBannerAdapter(split,this);
                mCount = bannerAdapter.getCount();
                details_viewPager.setAdapter(bannerAdapter);
            }
    }

    @Override
    public void showDataFail(String error) {

    }
    private void setListener() {
        details_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                details_text_showNum.setText((position + 1) + "/" + mCount);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //滑动变色
        details_detailScrollView.setScrollViewListener(new ProductDetailsView.ScrollViewListener() {
            @Override
            public void onScrollChange(ProductDetailsView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    details_relative_changer.setVisibility(View.GONE);
                    details_relativeLayout_relat.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    details_relative_changer.setVisibility(View.VISIBLE);
                    float scale = (float) t / 200;
                    float alpha = 255 * scale;
                    details_relativeLayout_relat.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                }
                if (0 < t && t < 700) {
                    details_text_good.setBackgroundColor(Color.RED);
                    details_text_detail.setBackgroundColor(Color.WHITE);
                    details_text_commen.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 1500) {
                    details_text_good.setBackgroundColor(Color.WHITE);
                    details_text_detail.setBackgroundColor(Color.RED);
                    details_text_commen.setBackgroundColor(Color.WHITE);
                } else if (t > 1500) {
                    details_text_good.setBackgroundColor(Color.WHITE);
                    details_text_detail.setBackgroundColor(Color.WHITE);
                    details_text_commen.setBackgroundColor(Color.RED);
                }
            }

        });

        details_text_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_detailScrollView.setScrollY(0);
            }
        });
        details_text_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_detailScrollView.setScrollY(702);
            }
        });
        details_text_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_detailScrollView.setScrollY(1501);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
        EventBus.getDefault().unregister(this);
    }
}
