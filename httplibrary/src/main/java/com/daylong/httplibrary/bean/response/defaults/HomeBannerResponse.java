package com.daylong.httplibrary.bean.response.defaults;

import java.io.Serializable;

/**
 * 首页轮播图
 */
public class HomeBannerResponse implements Serializable {


    private String title; //标题
    private Integer redirectType;//跳转类型
    private String imgUrl; //展示图片
    private String dynamicImgUrl; //动态图片
    private String description; //描述
    /**
     * 关联信息
     * 根据跳转类型做处理，1-无返回，仅展示，2-支付，跳支付页面，3-跳转的H5页面，4-邀请，跳转邀请页面
     */
    private String contactMsg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDynamicImgUrl() {
        return dynamicImgUrl;
    }

    public void setDynamicImgUrl(String dynamicImgUrl) {
        this.dynamicImgUrl = dynamicImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactMsg() {
        return contactMsg;
    }

    public void setContactMsg(String contactMsg) {
        this.contactMsg = contactMsg;
    }
}
