package com.daylong.arcx.pay;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.daylong.httplibrary.PayOrderBean;
import com.daylong.httplibrary.api.PayApi;
import com.daylong.httplibrary.bean.PayResult;
import com.daylong.httplibrary.bean.request.PayRequest;
import com.daylong.arcx.pay.mrg.PayListenerMrg;

import net.daylong.baselibrary.http.RetrofitFactory;
import net.daylong.baselibrary.http.base.BaseResponse;
import net.daylong.baselibrary.http.observer.BaseObserver;
import net.daylong.baselibrary.utils.JsonUtil;
import net.daylong.baselibrary.utils.rx.RxScheduler;
import net.daylong.baselibrary.utils.sys.AppUtil;
import net.daylong.baselibrary.utils.ui.ToastUtil;

import java.util.Map;

public class AliPay {
    private static final int SDK_PAY_FLAG = 1;
    private final Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            Object p = msg.what;

            switch (msg.what) {

                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    Map<String, String> msgMap = (Map<String, String>) msg.obj;
                    PayResult payResult = new PayResult(msgMap);
                    AppUtil.getMainHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            /**
                             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                             */
                            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                            String resultStatus = payResult.getResultStatus();
                            // 判断resultStatus 为9000则代表支付成功
                            if (TextUtils.equals(resultStatus, "9000")) {
                                String payRequest = msgMap.get("payRequest");

                                PayRequest payRequest1 = JsonUtil.fromJsonToObject(payRequest, PayRequest.class);
                                PayListenerMrg.getInstance().onPaySuc(payRequest1.getPayCommodityType(), msgMap.get("odNo"), payRequest1);
                                ToastUtil.show("支付成功");
                            } else {
                                ToastUtil.show("支付失败");
                                PayListenerMrg.getInstance().onPayFail();
                            }
                        }
                    }, 500);
                    break;
                }

            }


        }
    };

    public AliPay() {
    }


    public void pay(PayRequest payRequest) {

        // 提交获取阿里订单信息


        RetrofitFactory.getInstance().createApi(PayApi.class).aliPay(payRequest)
                .compose(RxScheduler.<BaseResponse<PayOrderBean>>rxSchedulerTransform())
                .subscribe(new BaseObserver<PayOrderBean>() {

                    @Override
                    protected void onStart() {


                    }

                    @Override
                    protected void onSuccess(PayOrderBean data) throws Exception {
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {

                                PayTask alipay = new PayTask(AppUtil.getCurrentActivity());
                                Map<String, String> result = alipay.payV2(data.getAliMsg().getBody(), true);
                                result.put("odNo", data.getOrderSn());
//                                result.put("post", payRequest.isPost() + "");
                                result.put("payRequest", JsonUtil.toJson(payRequest));
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessageDelayed(msg,1000);
                            }
                        };
                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();


                    }

                    @Override
                    protected void onFinish() {

                    }
                });
    }

}
