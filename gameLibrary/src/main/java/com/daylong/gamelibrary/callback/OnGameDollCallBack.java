package com.daylong.gamelibrary.callback;

import com.daylong.httplibrary.bean.AwardBean;

import java.util.ArrayList;
import java.util.List;

public interface OnGameDollCallBack {


    /**
     * 娃娃机回调
     *
     * @param isCatch 是否抓中
     * @param awardBeans 抓中列表
     *
     */
    void catchReturn(boolean isCatch, ArrayList<AwardBean> awardBeans);

    void catchDown();

}
