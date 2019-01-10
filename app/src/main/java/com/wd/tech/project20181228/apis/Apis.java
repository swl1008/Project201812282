package com.wd.tech.project20181228.apis;

public class Apis {
    //登录
    public static final String URL_LOGIN_POST = "user/v1/login";
    //注册
    public static final String URL_REGISTER_POST = "user/v1/register";
    //首页商品展示
    public static final String URL_SHOW_PRODUCTS = "commodity/v1/commodityList";
    //圈子展示
    public static final String URL_CIRCLE = "circle/v1/findCircleList?page=1&count=5";
    //首页搜索
    public static final String URL_HOME_SEARCH = "commodity/v1/findCommodityByKeyword?keyword=%s&page=1&count=20";
    //轮播图
    public static final String URL_HOME_BANNER = "commodity/v1/bannerShow";
    //商品详情
    public static final String URL_PRODUCT_DETAILS = "commodity/v1/findCommodityDetailsById?commodityId=%s";
    //添加购物车
    public static final String URL_ADD_CAR = "order/verify/v1/syncShoppingCart";
    //展示购物车
    public static final String URL_SHOW_CAR = "order/verify/v1/findShoppingCart";
}
