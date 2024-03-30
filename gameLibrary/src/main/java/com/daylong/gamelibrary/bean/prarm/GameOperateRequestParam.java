package com.daylong.gamelibrary.bean.prarm;

import com.daylong.gamelibrary.bean.bean.CharterSettlementMsg;

public class GameOperateRequestParam extends BaseGameRequestParam {

    private Integer fallNum; //得到的币
    private Integer goldNum; //余额

    private CharterSettlementMsg charterSettlementMsg; //包机结算信息

    public Integer getFallNum() {
        return fallNum;
    }

    public void setFallNum(Integer fallNum) {
        this.fallNum = fallNum;
    }

    public Integer getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Integer goldNum) {
        this.goldNum = goldNum;
    }

    public CharterSettlementMsg getCharterSettlementMsg() {
        return charterSettlementMsg;
    }

    public void setCharterSettlementMsg(CharterSettlementMsg charterSettlementMsg) {
        this.charterSettlementMsg = charterSettlementMsg;
    }
}
