package com.daylong.gamelibrary.request.charter;

import com.daylong.gamelibrary.meuns.GameCmdType;
import com.daylong.gamelibrary.request.BaseGameRequest;

/**
 * 包机
 */
public class CharterGameInfoRequest extends BaseGameRequest {

    public CharterGameInfoRequest() {
        super(GameCmdType.C2S_CHARTER_REFRESH.getCdm());
    }
}
