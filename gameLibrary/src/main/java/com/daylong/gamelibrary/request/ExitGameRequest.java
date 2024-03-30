package com.daylong.gamelibrary.request;

import com.daylong.gamelibrary.meuns.GameCmdType;

/**
 * 退出设备
 */
public class ExitGameRequest extends BaseGameRequest {
    public ExitGameRequest() {
        super(GameCmdType.C2S_EXIT_PRODUCT.getCdm());
    }
}
