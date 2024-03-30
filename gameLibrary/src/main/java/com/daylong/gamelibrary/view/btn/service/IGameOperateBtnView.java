package com.daylong.gamelibrary.view.btn.service;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.daylong.gamelibrary.meuns.GameOperateType;
import com.daylong.gamelibrary.request.operate.GameOperateDefaultRequest;

import net.daylong.baselibrary.utils.ui.view.button.MyImageBtn;
import net.daylong.gamesocket.mrg.WebSocketMrg;

public abstract class IGameOperateBtnView extends MyImageBtn {


    protected abstract GameOperateType getGameOperateType();

    public IGameOperateBtnView(@NonNull Context context) {
        super(context);

        initListener();


    }

    protected void initListener() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                Drawable buttonBackground = getDrawable(); // 获取按钮的背景Drawable

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonBackground.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.SRC_ATOP); // 灰色效果
                        setImageDrawable(buttonBackground);
                        sendMsg();
                        break;

                    //松开
                    case MotionEvent.ACTION_UP:
                        //移出
                    case MotionEvent.ACTION_CANCEL:
                        //松手时间差
                        buttonBackground.clearColorFilter(); // 清除灰色效果
                        setImageDrawable(buttonBackground);
                        sendStop();
                        break;
                }
                return true;
            }

        });
    }


    public void sendMsg() {
        WebSocketMrg.getInstance().sendMsg(new GameOperateDefaultRequest(getGameOperateType()));
    }

    public void sendStop() {
        WebSocketMrg.getInstance().sendMsg(new GameOperateDefaultRequest(GameOperateType.STOP));
    }

    public void setDown(boolean isDown) {
        Drawable buttonBackground = getDrawable(); // 获取按钮的背景Drawable

        if (isDown) {
            buttonBackground.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.SRC_ATOP); // 灰色效果
        } else {
            buttonBackground.clearColorFilter(); // 清除灰色效果
        }
        setEnabled(!isDown);
        setClickable(!isDown);

        setImageDrawable(buttonBackground);
    }

}
