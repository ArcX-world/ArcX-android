package com.daylong.musiclibrary.emums;
public enum DeviceWinningType {
    FREE_GAME(1, "免费游戏")//
    , GRM(2, "宝石游戏")//
    , PRIZE_WHEEL_GRAND(3, "大奖转盘-grand奖池")//
    , PRIZE_WHEEL_MAJOR(4, "大奖转盘-major奖池")//
    , PRIZE_WHEEL_MINOR(5, "大奖转盘-minor奖池")//
    , DRAGON_BALL(6, "龙珠")//
    , AVERAGE_AWARD(7, "普通奖励")//
    , AGYPT_OPEN_BOX(8, "埃及开箱子")//
    , GOSSIP(9, "八卦")//
    , COPPER_FULL(10, "铜钱集满")//
    , HERO_BATTLE(11, "三国战斗")//
    , THUNDER(12, "闪电")//
    , THUNDER_UP(13, "闪电UP")//
    , THUNDER_PRIZE_MINOR(14, "闪电minor彩金")//
    , THUNDER_PRIZE_MAJOR(15, "闪电major彩金")//
    , THUNDER_PRIZE_GRAND(16, "闪电grand彩金")//
    , THUNDER_COLLECT(17, "闪电收集")//

    ;


    private int code;
    private String msg;

    DeviceWinningType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
