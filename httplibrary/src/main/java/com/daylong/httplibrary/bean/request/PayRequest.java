package com.daylong.httplibrary.bean.request;

import com.daylong.httplibrary.bean.AwardBean;

import java.io.Serializable;

/**
 * 请求服务器 获取生成订单 信息
 */
public class PayRequest implements Serializable {
    private Integer payCommodityType; //支付类型
    private Integer productId; //设备ID
    private Integer commodityId; //商品ID
    private long num;
    private String imgUrl;


    public PayRequest(Integer payCommodityType, Integer productId, Integer commodityId, long num, String imgUrl) {
        this.payCommodityType = payCommodityType;
        this.productId = productId;
        this.commodityId = commodityId;
        this.num = num;
        this.imgUrl = imgUrl;
    }


    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPayCommodityType() {
        return payCommodityType;
    }

    public void setPayCommodityType(Integer payCommodityType) {
        this.payCommodityType = payCommodityType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }


    public AwardBean createAwardBean() {
        return new AwardBean(getPayCommodityType(), getImgUrl(), getNum());
    }

}
