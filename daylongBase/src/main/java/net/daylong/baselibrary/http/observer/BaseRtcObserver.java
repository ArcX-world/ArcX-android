package net.daylong.baselibrary.http.observer;

import android.accounts.NetworkErrorException;
import android.text.TextUtils;

import net.daylong.baselibrary.app.AppManager;
import net.daylong.baselibrary.http.ApiException;
import net.daylong.baselibrary.http.base.BaseResponse;
import net.daylong.baselibrary.http.base.BaseRtcResponse;
import net.daylong.baselibrary.utils.StringUtils;
import net.daylong.baselibrary.utils.ui.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class BaseRtcObserver<T> implements Observer<BaseRtcResponse<T>> {

    public BaseRtcObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    @Override
    public void onNext(BaseRtcResponse<T> tBaseResponse) {
        try {
            onSuccess(tBaseResponse.getRemotesdp(), tBaseResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof ApiException) {
                ApiException apiE = (ApiException) e;


                if (apiE.isExitApp()) {
                    AppManager.getInstance().exitApp();
                    return;
                }


                if (apiE.isTokenError()) {


                    //


                    AppManager.getInstance().returnToHome();

                } else {
                    if (apiE.isSignError() || StringUtils.isEmpty(apiE.getErrorMsg())) {


                    } else {
                        onError(apiE.getErrorMsg());
                        onError(apiE);
                    }
                }
            } else if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof SocketTimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof HttpException
                    || e instanceof UnknownHostException) {
                onError("");
            } else {
                onError("");
            }
            onFinish();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    /**
     * 请求开始返回
     */
    protected abstract void onStart();

    /**
     * 请求数据成功返回
     *
     * @param data
     * @throws Exception
     */
    protected abstract void onSuccess(T data) throws Exception;

    protected void onSuccess(T data, BaseRtcResponse<T> tBaseResponse) throws Exception {
        onSuccess(data);
    }


    /**
     * 请求token过期返回
     *
     * @throws Exception
     */
    protected void onCodeOverdue(String message) throws Exception {
        ToastUtil.show(message);
    }


    /**
     * 请求数据失败返回
     *
     * @param message
     */
    protected void onError(String message) throws Exception {
        if (!TextUtils.equals(message, "V1") && !TextUtils.equals(message, "fail")&&!TextUtils.isEmpty(message)) {
            ToastUtil.show(message);

        }
    }

    /**
     * 请求数据失败返回
     *
     * @param
     */
    protected void onError(ApiException apiException) throws Exception {

    }

    /**
     * 请求完成返回
     */
    protected abstract void onFinish();

}

