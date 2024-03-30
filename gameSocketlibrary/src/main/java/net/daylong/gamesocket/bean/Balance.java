package net.daylong.gamesocket.bean;

import java.io.Serializable;

public class Balance implements Serializable {

    private Long goldNum; //金币数量
    private Long integralNum; //积分数量

    public Long getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Long goldNum) {
        this.goldNum = goldNum;
    }

    public Long getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(Long integralNum) {
        this.integralNum = integralNum;
    }
}
