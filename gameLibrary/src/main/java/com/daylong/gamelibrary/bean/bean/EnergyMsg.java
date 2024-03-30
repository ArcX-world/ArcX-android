package com.daylong.gamelibrary.bean.bean;

public class EnergyMsg {


    private Integer cnAmt; //当前进度数量
    private Integer ttAmt; //总进度数量
    private Integer lfTm;//下次刷新时间

    public Integer getCnAmt() {
        return cnAmt;
    }

    public void setCnAmt(Integer cnAmt) {
        this.cnAmt = cnAmt;
    }

    public Integer getTtAmt() {
        return ttAmt;
    }

    public void setTtAmt(Integer ttAmt) {
        this.ttAmt = ttAmt;
    }

    public Integer getLfTm() {
        return lfTm;
    }

    public void setLfTm(Integer lfTm) {
        this.lfTm = lfTm;
    }
}
