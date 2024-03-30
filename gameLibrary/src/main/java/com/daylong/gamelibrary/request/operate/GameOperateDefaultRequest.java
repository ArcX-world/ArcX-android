package com.daylong.gamelibrary.request.operate;

import com.daylong.gamelibrary.meuns.GameOperateType;
import com.daylong.gamelibrary.request.base.BaseGameOperateRequest;

/**
 * 投币
 */
public class GameOperateDefaultRequest extends BaseGameOperateRequest {
    public GameOperateDefaultRequest(GameOperateType gameOperateType) {
        super(gameOperateType);
    }
}
