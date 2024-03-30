package com.daylong.gamelibrary.runnables;


import android.os.Handler;

import com.daylong.gamelibrary.callback.GameCountListener;
import com.daylong.gamelibrary.meuns.GameCmdType;
import com.daylong.gamelibrary.meuns.GameOperateType;
import com.daylong.gamelibrary.meuns.GameType;
import com.daylong.gamelibrary.request.BaseGameRequest;
import com.daylong.gamelibrary.request.RefreshTimeRequest;
import com.daylong.gamelibrary.request.operate.GameOperateDefaultRequest;

import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.gamesocket.mrg.WebSocketMrg;
import net.daylong.gamesocket.request.base.BaseMsg;

/**
 * 游戏倒計時
 */
public class GameCountdownRunnable {


    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {

            time--;
            if (time < 0) {
                return;
            }
            //提示
            gameCountListener.onTime(time);
            MyLogUtil.e("游戏倒计时-->" + time);

            if (time <= toastTime) {
                if (time == 0) {
                    gameCountListener.onEnd();
                    return;
                } else {
                    gameCountListener.onToastTime(time);
                }
            }
            handler.postDelayed(this, 1000);
        }
    };
    private Handler handler;
    private int time; //倒计时时间
    private GameType gameType; //倒计时时间
    private int toastTime; // 提示时间
    private GameCountListener gameCountListener; //回调

    public GameCountdownRunnable(GameType gameType, GameCountListener gameCountListener) {
        this.gameType = gameType;
        this.time = gameType.getGameTime();
        this.toastTime = gameType.getToastTime();
        this.gameCountListener = gameCountListener;
        handler = new Handler();
        MyLogUtil.e("PusherGameActivity>新建");
    }


    //刷新时间
    public void refreshTime() {
        time = gameType.getGameTime();
        MyLogUtil.e("PusherGameActivity>刷新");
        gameCountListener.onRefreshTime(time);
        stop();
        handler.postDelayed(runnable, 1000);
        WebSocketMrg.getInstance().sendMsg(new RefreshTimeRequest());
    }

    public void setGameTime(int time) {
        this.time = time;
        MyLogUtil.e("PusherGameActivity>设置时间+" + time);
        gameCountListener.onRefreshTime(time);
        stop();

        handler.postDelayed(runnable, 1000);
    }

    public void start() {
        if (handler == null) {
            return;
        }
        MyLogUtil.e("PusherGameActivity>开始倒计时");
        stop();
        time = gameType.getGameTime();
        handler.postDelayed(runnable, 1000);
    }

    public void stop() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void delete() {
        stop();
        handler = null;
    }
}
