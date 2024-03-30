package com.daylong.gamelibrary.request;

import com.daylong.gamelibrary.meuns.GameCmdType;

/**
 * 刷新时间
 */
public class RefreshGameTimeRequest extends BaseGameRequest {
    public RefreshGameTimeRequest() {
        super(GameCmdType.C2S_EXIT_PRODUCT.getCdm());
    }
}
