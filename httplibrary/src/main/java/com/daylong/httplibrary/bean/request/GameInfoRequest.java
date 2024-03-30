package com.daylong.httplibrary.bean.request;

/**
 * 请求设备信息
 */
public class GameInfoRequest {
    private int eqmId;

    public GameInfoRequest(int productId) {
        this.eqmId = productId;
    }

    public int getProductId() {
        return eqmId;
    }
}
