package com.daylong.gamelibrary.bean.bean;

public class CharterSettlementMsg {


    private Integer charterBalance; //包机余额
    private Integer returnNum; //包机返还
    private Integer totalNum; //合计获得

    public Integer getCharterBalance() {
        return charterBalance;
    }

    public void setCharterBalance(Integer charterBalance) {
        this.charterBalance = charterBalance;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
