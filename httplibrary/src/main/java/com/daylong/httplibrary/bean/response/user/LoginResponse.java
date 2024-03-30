package com.daylong.httplibrary.bean.response.user;

import java.io.Serializable;

public class LoginResponse implements Serializable {


    private String aesTkn; //调用凭证
    private Long aesOt;//调用凭证过期时间
    private String refTkn; //刷新凭证
    private Long refOt; //刷新凭证过期时间
    private Long arcxUid;//玩家ID

    public String getAccessToken() {
        return aesTkn;
    }

    public void setAccessToken(String accessToken) {
        this.aesTkn = accessToken;
    }

    public Long getAccessTokenOutTime() {
        return aesOt;
    }

    public void setAccessTokenOutTime(Long accessTokenOutTime) {
        this.aesOt = accessTokenOutTime;
    }

    public String getRefreshToken() {
        return refTkn;
    }

    public void setRefreshToken(String refreshToken) {
        this.refTkn = refreshToken;
    }

    public Long getRefreshTokenOutTime() {
        return refOt;
    }

    public void setRefreshTokenOutTime(Long refreshTokenOutTime) {
        this.refOt = refreshTokenOutTime;
    }

    public Long getUserId() {
        return arcxUid;
    }

    public void setUserId(Long userId) {
        this.arcxUid = userId;
    }
}
