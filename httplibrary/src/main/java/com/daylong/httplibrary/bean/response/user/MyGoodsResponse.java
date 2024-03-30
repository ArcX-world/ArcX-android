package com.daylong.httplibrary.bean.response.user;

public class MyGoodsResponse {


    private long awardId; //id
    private String awardName; //奖励名称
    private String awardImgUrl; //奖励图片
    private Integer awardNum; //奖励数量
    private String trackingNo; //快递单号

    public long getAwardId() {
        return awardId;
    }

    public void setAwardId(long awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardImgUrl() {
        return awardImgUrl;
    }

    public void setAwardImgUrl(String awardImgUrl) {
        this.awardImgUrl = awardImgUrl;
    }

    public Integer getAwardNum() {
        return awardNum;
    }

    public void setAwardNum(Integer awardNum) {
        this.awardNum = awardNum;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
}
