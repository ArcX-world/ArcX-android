package com.daylong.musiclibrary.bean;
public class DeviceMusicBean {
    private Integer productId; //设备ID
    private Integer isStart; //传入0 代表结束 1代表开始
    private Integer isEndSwitch; //传入 0 代表 只有 开始没有结束， 1:代表 有开始和结束
    private Integer productType;
    private Integer voiceType; //播放类型

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getIsStart() {
        return isStart;
    }

    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }

    public Integer getIsEndSwitch() {
        return isEndSwitch;
    }

    public void setIsEndSwitch(Integer isEndSwitch) {
        this.isEndSwitch = isEndSwitch;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(Integer voiceType) {
        this.voiceType = voiceType;
    }


    /**
     * 是否播放
     *
     * @return
     */
    public boolean isPlay() {
        return isEndSwitch == 0 || isStart == 1;
    }

    public boolean isStop() {
        return isStart == 0;
    }
}
