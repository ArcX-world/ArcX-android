package com.daylong.gamelibrary.view.title;

import android.content.Context;

import androidx.annotation.NonNull;

import net.daylong.baselibrary.bean.IUserInfo;
import net.daylong.baselibrary.utils.ui.layout.cl.ConstraintLayoutView;

public abstract class IGameUserView extends ConstraintLayoutView {
    public IGameUserView(@NonNull Context context) {
        super(context);
    }


    /**
     * 設置用戶信息
     *
     * @param info
     */
    public abstract void setGameUserInfo(IUserInfo info);

}
