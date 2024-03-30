package com.daylong.userlibrary.enums;


import com.daylong.httplibrary.bean.request.user.LoginRequest;

public enum LoginType {
    TOURIST(1, "游客登陆")//
    , WECHAT(3, "微信登录")//
    ;

    private int id;
    private String desc;

    LoginType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public LoginRequest createTourist() {
        return createWechat(null);
    }

    public LoginRequest createWechat(String wgTkn) {
        return new LoginRequest(this.getId(), wgTkn);
    }
}
