package com.daylong.gamelibrary.callback;

import android.view.MotionEvent;
import android.view.View;

import com.daylong.gamelibrary.act.BaseGameActivity;
import com.daylong.gamelibrary.meuns.GameType;
import com.daylong.musiclibrary.emums.SoundPoolType;

import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.baselibrary.utils.sys.AppUtil;

public class PushCoinListener implements View.OnTouchListener {

    private static final long AUTO_TIME = 3000; //长按执行开始自动
    private static final String TAG = "投币触摸->";

    private Action action = Action.NONE; //


    private int autoPushCoinTime;
    private int downPushCoinTime;


    private DnPlayView dnPlayView;
    private BaseGameActivity activity;

    /**
     * @param autoPushCoinTime // 自动投币时间
     * @param downPushCoinTime // 点击投币时间
     */
    public PushCoinListener(DnPlayView dnPlayView, BaseGameActivity activity, OnTouchPushCoinListener onTouchPushCoinListener, int autoPushCoinTime, int downPushCoinTime) {
        this.activity = activity;
        this.onTouchPushCoinListener = onTouchPushCoinListener;
        this.dnPlayView = dnPlayView;
        this.autoPushCoinTime = autoPushCoinTime;
        this.downPushCoinTime = downPushCoinTime;
    }

    private long pushCoinTime;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            long c = currentTimeMillis - pushCoinTime;

            MyLogUtil.e(TAG, "投币->时间差" + c);

            if (c >= autoPushCoinTime) {
                MyLogUtil.e(TAG, "投币->");
                if (action == Action.NONE) {
                    SoundPoolType.PUSH_COIN.play();
                }
                onTouchPushCoinListener.onPushCoin();
                pushCoinTime = currentTimeMillis;
            } else {
                MyLogUtil.e(TAG, "投币->不能投幣->");

            }

            AppUtil.getMainHandler().postDelayed(runnable, getPushCoinTime());

        }
    };

    /**
     * 获取投币时间
     *
     * @return
     */
    private int getPushCoinTime() {
        return action == Action.NONE ? downPushCoinTime : autoPushCoinTime;
    }


    private long downTime;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (AppUtil.isHorizontal() || !activity.isMyStart() || activity.getGameType() != GameType.PUSH_COIN_MACHINE) {
            MyLogUtil.e(TAG, "不触发投币状态");
            return false;
        }
        int action = event.getAction();
        MyLogUtil.e(TAG, "action->" + action);


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();
                //按下,启动
                AppUtil.getMainHandler().removeCallbacks(runnable, null);
                AppUtil.getMainHandler().postDelayed(runnable, 0);
                MyLogUtil.e(TAG, "按下时间->" + downTime);

                float x = event.getX();
                float y = event.getY();
                if (onTouchPushCoinListener != null) {
                    onTouchPushCoinListener.onDown(x, y, this.action);
                }
                if (this.action == Action.NONE) {
                    // 播放自动的动画
//                    dnPlayView.getAutoView().showAutoAnim(x, y);
                }
                break;
            //松开
            case MotionEvent.ACTION_UP:
                //移出
            case MotionEvent.ACTION_CANCEL:
                //松手时间差
//                dnPlayView.getAutoView().hintAuto();
                MyLogUtil.e(TAG, "松手->当前状态" + this.action);

                if (this.action == Action.NONE) {
                    long difference = System.currentTimeMillis() - downTime;
                    //如果时间差大于等于自动时间
                    MyLogUtil.e(TAG, "松手->时间差" + difference);

                    if (difference >= AUTO_TIME) {
                        //启动自动
                        // 变更状态为自动.
                        this.action = Action.AUTO;
                        if (onTouchPushCoinListener != null) {
                            onTouchPushCoinListener.onStartAuto();
                        }
//                        dnPlayView.getAutoView().showAutoToast();
//                        WebSocketMrg.getInstance().sendMsg(StartGameRequest.getInstance().setOperateState(OperateState.AUTO_PUSH_COIN));
                        //AppUtil.getMainHandler().removeCallbacks(runnable, null);
                        return false;
                    }
                } else if (this.action == Action.AUTO) {
//                    SoundPoolType.GAME_AUTO_STOP.play();
//                    dnPlayView.getAutoView().showAutoStopToast();
//                    WebSocketMrg.getInstance().sendMsg(StartGameRequest.getInstance().setOperateState(OperateState.CANCEL_AUTO_PUSH_COIN));
                    if (onTouchPushCoinListener != null) {
                        onTouchPushCoinListener.onCloseAuto();
                    }
                }

                this.action = Action.NONE;
                AppUtil.getMainHandler().removeCallbacks(runnable, null);
                MyLogUtil.e(TAG, "松手->取消自动");

                break;
        }


        return false;
    }


    //關閉
    public void close() {
        activity = null;
        AppUtil.getMainHandler().removeCallbacks(runnable, null);
    }

    //
    public enum Action {
        NONE, // 默认
        AUTO; // 自动
    }

    private OnTouchPushCoinListener onTouchPushCoinListener;

    public void setOnTouchPushCoinListener(OnTouchPushCoinListener onTouchPushCoinListener) {
        this.onTouchPushCoinListener = onTouchPushCoinListener;
    }

    /**
     * 投币回调
     */
    public interface OnTouchPushCoinListener {

        void onDown(float x, float y, Action action);

        void onStartAuto();

        void onCloseAuto();

        void onPushCoin();

    }
}
