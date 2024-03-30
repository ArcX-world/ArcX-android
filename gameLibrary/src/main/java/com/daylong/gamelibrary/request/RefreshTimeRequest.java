package com.daylong.gamelibrary.request;

import com.daylong.gamelibrary.meuns.GameCmdType;

/**
 * 进入设备
 */
public class RefreshTimeRequest extends BaseGameRequest {
    public RefreshTimeRequest() {
        super(GameCmdType.C2S_REFRESH_TIME.getCdm());
    }
}
