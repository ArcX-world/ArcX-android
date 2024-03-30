package com.daylong.basecache;

public interface CacheConstants {
    String USER_INFO = "userInfo";
    String USER_ID = "userId";
    String ACCESS_TOKEN = "accessToken"; //请求Token
    String REFRESH_TOKEN = "refreshToken"; //刷新Token
    String BASE_URL = "base_url";


    //設備

    String DEVICE_ID = "did"; //计算
    String SYS_PACK_NAME = "SPN"; //计算
    String SYS_VERSION_CODE = "Svc"; //计算


    //游戏

    //游戏房间Id
    String GAME_ROOM_ID = "grd";

    // 游戲CMD
    String GAME_ROOM_CMD = "grc";
    String GAME_LAST_TIME = "glt";
    /**
     * 包机信息
     */
    String GAME_CHARTER_DESC = "gCD";


    //倍率
    String GAME_MULTIPLIER = "gm";
}
