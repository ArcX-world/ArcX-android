package com.daylong.gamelibrary.view.btn.arcade;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.daylong.gamelibrary.view.btn.service.IGameOperateBtnView;

import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.baselibrary.utils.sys.AppUtil;
import net.daylong.baselibrary.utils.ui.ToastUtil;

public abstract class ArcadeBaseView extends IGameOperateBtnView implements View.OnTouchListener {

    public ArcadeBaseView(@NonNull Context context) {
        super(context);
    }

    private static final long AUTO_TIME = 3000; //长按执行开始自动
    private static final String TAG = "youi投币触摸->";

    protected  abstract  String getBtnName();

    private Action action = Action.NONE; //


    protected int autoPushCoinTime() {
        return 333;
    }

    ;//自动投币时间

    protected int downPushCoinTime() {
        return 333;
    }

    ; //点击投币时间


    private long pushCoinTime;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            long c = currentTimeMillis - pushCoinTime;

            MyLogUtil.e(TAG, "投币->时间差" + c);

            if (c >= autoPushCoinTime()) {
                sendMsg();
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
        return action == Action.NONE ? downPushCoinTime() : autoPushCoinTime();
    }


    private long downTime;

    @Override
    protected void initListener() {


        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        int action = event.getAction();
        MyLogUtil.e(TAG, "action->" + action);

        Drawable buttonBackground = getDrawable(); // 获取按钮的背景Drawable

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();
                //按下,启动
                AppUtil.getMainHandler().removeCallbacks(runnable, null);
                AppUtil.getMainHandler().postDelayed(runnable, 0);
                MyLogUtil.e(TAG, "按下时间->" + downTime);
                buttonBackground.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.SRC_ATOP); // 灰色效果
                setImageDrawable(buttonBackground);
                break;
            //松开
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //松手时间差
                MyLogUtil.e(TAG, "松手->当前状态" + this.action);
                if (this.action == Action.NONE) {
                    long difference = System.currentTimeMillis() - downTime;
                    //如果时间差大于等于自动时间
                    MyLogUtil.e(TAG, "松手->时间差" + difference);

                    if (difference >= AUTO_TIME) {
                        //启动自动
                        // 变更状态为自动.
                        this.action = Action.AUTO;
                        ToastUtil.show("开启"+getBtnName()+"自动");

                        return true;
                    }
                } else if (this.action == Action.AUTO) {
                    MyLogUtil.e(TAG, "松手->取消自动");

                    ToastUtil.show("取消"+getBtnName()+"自动");

                }

                this.action = Action.NONE;
                AppUtil.getMainHandler().removeCallbacks(runnable, null);
                buttonBackground.clearColorFilter(); // 清除灰色效果
                setImageDrawable(buttonBackground);

                break;
        }


        return true;
    }

    //
    public enum Action {
        NONE, // 默认
        AUTO; // 自动
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AppUtil.getMainHandler().removeCallbacks(runnable, null);
    }
}
