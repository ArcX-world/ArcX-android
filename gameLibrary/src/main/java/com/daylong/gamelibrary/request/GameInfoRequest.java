package com.daylong.gamelibrary.request;

import com.daylong.gamelibrary.meuns.GameCmdType;

/**
 * 游戲信息
 */
public class GameInfoRequest extends BaseGameRequest {
    public GameInfoRequest() {
        super(GameCmdType.C2S_ROOM_MSG.getCdm());
    }
}
