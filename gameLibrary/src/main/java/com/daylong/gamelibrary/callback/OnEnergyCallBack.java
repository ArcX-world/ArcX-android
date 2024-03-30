package com.daylong.gamelibrary.callback;

import com.daylong.gamelibrary.bean.bean.EnergyMsg;

public interface OnEnergyCallBack {


    /**
     *
     * @param cnAmt 当前进度数量
     * @param ttAmt 总进度数量
     * @param lfTm 下次刷新时间
     */
    void onEnergy(int cnAmt, int ttAmt, int lfTm);
}
