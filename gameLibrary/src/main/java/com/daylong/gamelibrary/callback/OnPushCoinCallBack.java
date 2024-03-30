package com.daylong.gamelibrary.callback;

import com.daylong.httplibrary.bean.AwardBean;
import com.daylong.httplibrary.bean.game.dragon.DragonBallRewardBean;
import com.daylong.musiclibrary.bean.DeviceWinningBean;

import java.util.ArrayList;

/**
 * 推币机回调
 */
public interface OnPushCoinCallBack {


    /**
     * 龙珠
     * @param dragonBallNum
     */
    void onDragonBall(int dragonBallNum, ArrayList<AwardBean> awardBeans);
    void onDragonBallMary(DragonBallRewardBean dragonBallRewardBean);
    void onGameWinning(DeviceWinningBean deviceWinningBean);

}
