package com.daylong.gamelibrary.meuns;

public enum GameOperateType {

    START(0, "开始游戏"),//
    FORWARD(1, "向前"),//
    BACKWARDS(2, "向后"),//
    LEFT(3, "向左"),////
    RIGHT(4, "向右"),//
    STOP(5, "停止"),//
    CATCH(6, "下抓"),//
    PUSH_COIN(8, "投币"),//
    OFF_LINE(9, "下机"),//
    CATCH_RESULT(11, "抓娃娃結果返回"),//
    ROCK(13, "摇摆"),//
    GET_COIN(14, "获得币"),//
    SHOOT(16, "发射"),//
    CHANGE_GUN(17, "换跑"),//
    SETTLEMENT(18, "只进行结算"),//
    AUTO_SHOOT(20, "自动发炮"),//
    CANCEL_AUTO_SHOOT(21, "自动发炮"),//
    AUTO_PUSH_COIN(24, "自动投币"),//
    CANCEL_AUTO_PUSH_COIN(25, "取消自动投币"),//


    CHECK_PRODUCT(19, "检测设备")//
    , ERROR_CHECK(22, "故障检测"),
    CHARTER_SETTLEMENT(23, "结束包机")//
    ;//


    private int code;
    private String desc;

    GameOperateType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static GameOperateType getOperateState(int code) {

        for (GameOperateType value : GameOperateType.values()) {
            if (value.getCode() == code) {
                return value;
            }


        }

        return null;

    }
}
