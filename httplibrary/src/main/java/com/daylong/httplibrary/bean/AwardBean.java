package com.daylong.httplibrary.bean;

import java.io.Serializable;

public class AwardBean implements Serializable {

    private Integer cmdTp; //奖励类型
    private String awdPct; //奖励图片
    private Long awdAmt; //奖励数量

    public AwardBean(Integer commodityType, String awardImgUrl, Long awardNum) {
        this.cmdTp = commodityType;
        this.awdPct = awardImgUrl;
        this.awdAmt = awardNum;
    }

    public AwardBean() {
    }


    public Integer getCommodityType() {
        return cmdTp;
    }

    public void setCommodityType(Integer commodityType) {
        this.cmdTp = commodityType;
    }

    public String getAwardImgUrl() {
        return awdPct;
    }

    public void setAwardImgUrl(String awardImgUrl) {
        this.awdPct = awardImgUrl;
    }

    public Long getAwardNum() {
        return awdAmt;
    }

    public void setAwardNum(Long awardNum) {
        this.awdAmt = awardNum;
    }
}
