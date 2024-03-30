package com.daylong.httplibrary.bean.response.game.charter;

import java.io.Serializable;

public class CharterMsg implements Serializable {

    private String chBl; //包机余额
    private String resQt; //包机返还
    private String tolQt; //合计获得

    public String getBackNum() {
        return chBl;
    }

    public void setBackNum(String backNum) {
        this.chBl = backNum;
    }

    public String getBalance() {
        return resQt;
    }

    public void setBalance(String balance) {
        this.resQt = balance;
    }



    public String getSumNum() {
        return tolQt;
    }

    public void setSumNum(String sumNum) {
        this.tolQt = sumNum;
    }
}
