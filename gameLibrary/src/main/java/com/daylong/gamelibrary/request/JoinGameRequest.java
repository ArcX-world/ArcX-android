package com.daylong.gamelibrary.request;

import com.daylong.gamelibrary.meuns.GameCmdType;

/**
 * 进入设备
 */
public class JoinGameRequest extends BaseGameRequest {
    public JoinGameRequest() {
        super(GameCmdType.C2S_JOIN_PRODUCT.getCdm());
    }
}
