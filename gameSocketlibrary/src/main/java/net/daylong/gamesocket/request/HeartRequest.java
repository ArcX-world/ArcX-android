package net.daylong.gamesocket.request;

import net.daylong.gamesocket.request.base.BaseMsg;
import net.daylong.gamesocket.response.HeartBeatParam;

/**
 * 心跳
 */
public class HeartRequest extends BaseMsg<HeartBeatParam> {
    public HeartRequest() {
        super(1001,new HeartBeatParam());
    }


}
