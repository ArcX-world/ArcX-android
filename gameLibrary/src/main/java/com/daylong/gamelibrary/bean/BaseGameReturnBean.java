package com.daylong.gamelibrary.bean;

import com.daylong.httplibrary.bean.response.user.UserInfoResponse;

import java.io.Serializable;

//返回信息
public class BaseGameReturnBean extends UserInfoResponse  implements Serializable {

    private int devId;

    public int getProductId() {
        return devId;
    }

    public void setProductId(int productId) {
        this.devId = productId;
    }
}
