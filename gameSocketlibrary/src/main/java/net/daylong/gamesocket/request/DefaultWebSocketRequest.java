package net.daylong.gamesocket.request;

import net.daylong.gamesocket.request.base.BaseMsg;
import net.daylong.gamesocket.request.base.BaseParam;

/**
 * 心跳
 */
public class DefaultWebSocketRequest extends BaseMsg<BaseParam> {
    public DefaultWebSocketRequest(int cmd) {
        super(cmd,new BaseParam());
    }


}
