package com.daylong.gamelibrary.meuns;

import java.io.Serializable;

public enum GameType implements Serializable {
    PUSH_COIN_MACHINE(1, "推币机", 2001, 60, 10)//
    , DOLL_MACHINE(2, "娃娃机", 2003, 30, 10)//
    , BALL_MACHINE(3, "礼品机", 2005, 60, 30)//
    ;

    private int id;
    private String desc;
    private int cmd; //命令
    private int gameTime; //游戏时间
    private int toastTime; //提示时间

    GameType(int id, String desc, int cmd, int gameTime, int toastTime) {
        this.id = id;
        this.desc = desc;
        this.cmd = cmd;
        this.gameTime = gameTime;
        this.toastTime = toastTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getToastTime() {
        return toastTime;
    }

    public void setToastTime(int toastTime) {
        this.toastTime = toastTime;
    }

    public static GameType getGameTypeById(int id) {


        for (GameType gameType : values()) {
            if (gameType.getId() == id) {
                return gameType;
            }

        }

        return null;

    }
}
