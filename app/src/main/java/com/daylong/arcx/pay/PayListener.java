package com.daylong.arcx.pay;

import com.daylong.httplibrary.bean.request.PayRequest;

/**
 * 支付回调
 */
public interface PayListener {


    /**
     * @param payType 支付类型
     * @param odNo    支付ID
     */
    void onPaySuc(int payType, String odNo, PayRequest payRequest);

    void onPayFail();

}
