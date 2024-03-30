package com.daylong.httplibrary.bean.response.user;

import net.daylong.baselibrary.bean.IUserInfo;

import java.io.Serializable;

public class UserInfoResponse implements Serializable, IUserInfo {



    private String email;
    private Long plyId; // 用户ID
    private String plyNm; // 昵称
    private String plyPct; //头像
    private Integer svTp; //服务端类型
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String getUserName() {
        return plyNm;
    }

    @Override
    public String getUserImgUrl() {
        return plyPct;
    }

    @Override
    public Long getUserId() {
        return plyId;
    }


    public Long getPlyId() {
        return plyId;
    }

    public void setPlyId(Long plyId) {
        this.plyId = plyId;
    }

    public String getPlyNm() {
        return plyNm;
    }

    public void setPlyNm(String plyNm) {
        this.plyNm = plyNm;
    }

    public String getPlyPct() {
        return plyPct;
    }

    public void setPlyPct(String plyPct) {
        this.plyPct = plyPct;
    }

    public Integer getSvTp() {
        return svTp;
    }

    public void setSvTp(Integer svTp) {
        this.svTp = svTp;
    }
}
