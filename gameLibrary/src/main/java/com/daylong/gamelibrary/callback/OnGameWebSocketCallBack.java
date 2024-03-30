package com.daylong.gamelibrary.callback;

import com.daylong.gamelibrary.bean.GameInfoBean;

public interface OnGameWebSocketCallBack {


    /**
     * 开始游戏 成功
     */
    void startGameSuc();

    /**
     * 房间信息
     *
     * @param gameInfoBean 房间信息
     */
    void gameInfo(GameInfoBean gameInfoBean);


    /**
     * 刷新时间
     */
    void onRefreshTime();

    /**
     * 返回获得币
     *
     * @param coin
     */
    void onCoinReturn(long coin);

    /**
     * 包机返回
     *
     * @param charterBalance
     * @param leftTime
     * @param endTime
     */
    void onChart(long charterBalance, int leftTime, long endTime);

    /**
     * 包机结束返回
     *
     * @param charterBalance
     * @param returnNum
     * @param totalNum
     */
    void onChartEndReturn(long charterBalance, long returnNum, long totalNum);




    /**
     * 用户能量
     *
     * @param cnAmt //当前进度数量
     * @param ttAmt //总进度数量
     * @param cgAmt //变更数量
     * @param lfTm //下次刷新时间
     */
    void onEnergy(int cnAmt, int ttAmt,long cgAmt, int lfTm);
}
