package com.daylong.musiclibrary.bean;


import com.daylong.musiclibrary.emums.DeviceWinningType;

/**
 * 设备中奖
 */
public class DeviceWinningBean {
    private Integer devId; //设备ID
    private Integer devAwdTp; //中奖类型
    private Integer awdAmt; //中奖数量
    private Integer stFlg; // 是否开始中奖
    private Integer awardMulti; // 中奖得分倍

    public Integer getAwardMulti() {
        return awardMulti;
    }

    public void setAwardMulti(Integer awardMulti) {
        this.awardMulti = awardMulti;
    }

    public Integer getProductId() {
        return devId;
    }

    public void setProductId(Integer productId) {
        this.devId = productId;
    }

    public Integer getProductAwardType() {
        return devAwdTp;
    }

    public void setProductAwardType(Integer productAwardType) {
        this.devAwdTp = productAwardType;
    }

    public Integer getAwardNum() {
        return awdAmt;
    }

    public void setAwardNum(Integer awardNum) {
        this.awdAmt = awardNum;
    }

    public Integer getIsStart() {
        return stFlg;
    }

    public boolean isStart() {
        return stFlg == 1;
    }

    public void setIsStart(Integer isStart) {
        this.stFlg = isStart;
    }


    public boolean isOrdinary() {
        return getProductAwardType() != null && getProductAwardType() == 7;
    }

    /**
     * 龙珠
     *
     * @return
     */
    public boolean isDragonBall() {
        if (devAwdTp == null) {
            return false;
        }
        return devAwdTp == DeviceWinningType.DRAGON_BALL.getCode();
    }


    public boolean isPay() {
        return devAwdTp < 13 || devAwdTp > 17;
    }
}
