package com.daylong.httplibrary;


/**
 * 生成订单
 */
public class PayOrderBean {

    private String odNo;

    private AliPayInfo aliMsg;

    public String getOrderSn() {
        return odNo;
    }

    public void setOrderSn(String orderSn) {
        this.odNo = orderSn;
    }

    public AliPayInfo getAliMsg() {
        return aliMsg;
    }

    public void setAliMsg(AliPayInfo aliMsg) {
        this.aliMsg = aliMsg;
    }
}
