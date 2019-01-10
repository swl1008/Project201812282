package com.wd.tech.project20181228.bean;


public class ShopCarBean {

    private int commodityId;
    private String commodityName;
    private int count;
    private String pic;
    private int price;
    private boolean isCheck = false;

    public ShopCarBean(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
    //    private String message;
//    private String status;
//    private List<ShowCarBean.ResultBean> result;
//
//    public ShopCarBean(int commodityId, int count) {
//
//    }
//
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public List<ShowCarBean.ResultBean> getResult() {
//        return result;
//    }
//
//    public void setResult(List<ShowCarBean.ResultBean> result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//
//        private int commodityId;
//        private String commodityName;
//        private int count;
//        private String pic;
//        private int price;
//        private boolean isCheck = false;
//
//
//
//
//        public boolean isCheck() {
//            return isCheck;
//        }
//
//        public void setCheck(boolean check) {
//            isCheck = check;
//        }
//
//        public int getCommodityId() {
//            return commodityId;
//        }
//
//        public void setCommodityId(int commodityId) {
//            this.commodityId = commodityId;
//        }
//
//        public String getCommodityName() {
//            return commodityName;
//        }
//
//        public void setCommodityName(String commodityName) {
//            this.commodityName = commodityName;
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        public String getPic() {
//            return pic;
//        }
//
//        public void setPic(String pic) {
//            this.pic = pic;
//        }
//
//        public int getPrice() {
//            return price;
//        }
//
//        public void setPrice(int price) {
//            this.price = price;
//        }
//    }
}
