package com.daylong.httplibrary.bean.request.user;

import java.io.Serializable;

/**
 * 登录请求信息
 */
public class InvitationRequest implements Serializable {

    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public InvitationRequest(String inviteCode) {
        this.inviteCode = inviteCode;
    }

}
