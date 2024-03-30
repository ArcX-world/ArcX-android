package com.daylong.gamelibrary.meuns;

public enum GameCmdType {
    C2S_REFRESH_TIME(1201, 1202, "刷新时间")//
    , C2S_ROOM_MSG(1203, 1204, "房间信息")//

    , C2S_JOIN_PRODUCT(1205, 1206, "进入设备")//
    , C2S_EXIT_PRODUCT(1207, 1205, "退出设备")//
    , S2C_KICK_OUT_PRODUCT(null, 1208, "退出设备")//

    //包机
    , C2S_CHARTER(1111, 1112, "请求包机")//
    , C2S_CHARTER_REFRESH(1113, 1114, "包机刷新信息接口")//
    //设备操作
    , GAME_CMD_PUSH(2001, 2002, "推币机")//
    , GAME_CMD_DOLL(2003, 2004, "娃娃机")//
    , GAME_CMD_BALL(2005, 2006, "礼品机")//
    //能量
    , C2S_ENERGY_MSG(1101, 1102, "能量")//
    //中奖
    , S2C_INNO_WIN_PRIZE(null, 1210, "自研设备中奖")//
    , C2S_INNO_DRAGON_MSG(1225, 1226, "龙珠")//
    , S2C_INNO_DRAGON_AWARD_MSG(null, 1228 ,"龙珠中奖")//

    ;

    private Integer cdm;
    private Integer returnCdm;
    private String desc;


    public static GameCmdType getGameCmdType(int cmd) {
        for (GameCmdType value : GameCmdType.values()) {
            if (value.getCdm() == cmd) {
                return value;
            }


        }
        return null;

    }

    public static GameCmdType getGameReturnCmdType(int cmd) {
        for (GameCmdType value : GameCmdType.values()) {
            if (value.getReturnCdm() == cmd) {
                return value;
            }


        }
        return null;

    }

    GameCmdType(Integer cdm, Integer returnCdm, String desc) {
        this.cdm = cdm;
        this.returnCdm = returnCdm;
        this.desc = desc;
    }

    public Integer getCdm() {
        return cdm;
    }

    public void setCdm(Integer cdm) {
        this.cdm = cdm;
    }

    public Integer getReturnCdm() {
        return returnCdm;
    }

    public void setReturnCdm(Integer returnCdm) {
        this.returnCdm = returnCdm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
