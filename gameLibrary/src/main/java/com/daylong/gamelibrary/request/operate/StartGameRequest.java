package com.daylong.gamelibrary.request.operate;

import com.daylong.gamelibrary.meuns.GameOperateType;
import com.daylong.gamelibrary.request.base.BaseGameOperateRequest;

/**
 * 开始游戏
 */
public class StartGameRequest extends BaseGameOperateRequest {
    public StartGameRequest() {
        super(GameOperateType.START);
    }
}
