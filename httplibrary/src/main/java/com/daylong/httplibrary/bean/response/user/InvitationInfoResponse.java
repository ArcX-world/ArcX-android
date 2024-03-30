package com.daylong.httplibrary.bean.response.user;

public class InvitationInfoResponse {

    private String invoteCode;//邀请码
    private Integer bindFlag; //是否已经绑定
    private String shareUrl; //分享链接
    private String shareTitle; //分享标题
    private String shareDesc; //分享描述

    public String getInvoteCode() {
        return "ID：" + invoteCode;
    }

    public void setInvoteCode(String invoteCode) {
        this.invoteCode = invoteCode;
    }

    public Integer getBindFlag() {
        return bindFlag;
    }

    public boolean isBind() {
        return bindFlag == 1;
    }

    public void setBindFlag(Integer bindFlag) {
        this.bindFlag = bindFlag;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }
}
