package com.daylong.gamelibrary.callback;

/**
 * 游戏倒计时
 */
public interface GameCountListener {

    void onToastTime(int time);

    void onEnd();
    void onTime(int time);
    void onRefreshTime(int time);

}
