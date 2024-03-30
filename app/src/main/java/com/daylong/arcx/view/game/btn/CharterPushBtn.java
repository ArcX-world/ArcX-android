package com.daylong.arcx.view.game.btn;

import android.content.Context;

import androidx.annotation.NonNull;

import com.daylong.gamelibrary.view.btn.ICharterPushCoinBtn;
import com.daylong.arcx.R;

public class CharterPushBtn extends ICharterPushCoinBtn {
    public CharterPushBtn(@NonNull Context context, int btnReg, String charterTitle, int charterTitleColor, int shadowColor, int icon, int coinColor) {
        super(context, btnReg, charterTitle, charterTitleColor, shadowColor, icon, coinColor);

    }

    public CharterPushBtn(@NonNull Context context) {
        this(context, R.drawable.img_mch_alert_btn, "包机余额", R.color.color_white, R.color.color_c2001da7, R.drawable.img_coin, R.color.color_white);
    }

}
