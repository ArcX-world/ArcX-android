package com.daylong.httplibrary.bean.response.game.charter;

import java.io.Serializable;
import java.util.List;

/**
 * 包机提示内容
 */
public class CharterDescResponse implements Serializable {


    private String desc;
    private String endNotice;
    private List<CommodityListDTO> commodityList;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEndNotice() {
        return endNotice;
    }

    public void setEndNotice(String endNotice) {
        this.endNotice = endNotice;
    }

    public List<CommodityListDTO> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListDTO> commodityList) {
        this.commodityList = commodityList;
    }

    public static class CommodityListDTO implements Serializable {
        private Integer commodityId;
        private Integer price;

        public Integer getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(Integer commodityId) {
            this.commodityId = commodityId;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public  String getBtnStr(){
            return "支付"+getPrice()+"元";
        }

    }
}


