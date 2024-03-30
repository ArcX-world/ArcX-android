package com.daylong.httplibrary.model.contract.wallet;


import net.daylong.baselibrary.http.base.BaseResponse;
import net.daylong.baselibrary.http.mvp.BasePresenter;
import net.daylong.baselibrary.http.view.IViewBaseModel;
import net.daylong.baselibrary.http.view.IViewBaseView;

import io.reactivex.Observable;

/**
 * 提现请求
 */
public interface WithdrawalContract {
    interface WithdrawalModel extends IViewBaseModel {

        /**
         * 提现
         *
         * @param tkTp 提现类型
         * @param amt  提下今日
         * @return
         */
        Observable<BaseResponse<Object>> postWithdrawal(int tkTp, double amt);


    }

    interface WithdrawalView extends IViewBaseView {


        void onWithdrawalSuc();


    }

    abstract class WithdrawalPresenter extends BasePresenter<WithdrawalModel, WithdrawalView> {

        public abstract void postWithdrawal(int tkTp, double amt);

    }
}
