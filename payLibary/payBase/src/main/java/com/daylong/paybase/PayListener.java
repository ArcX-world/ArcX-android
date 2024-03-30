package com.daylong.paybase;


import com.daylong.paybase.emuns.PayType;

public interface PayListener {


    /**
     * 支付成功
     *
     * @param type
     */
    void onPayScu(PayType type,String msg);

    /**
     * 支付失败
     *
     * @param code
     * @param msg
     */
    void onPayError(int code, String msg);

}
