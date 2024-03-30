package com.daylong.httplibrary.bean.menus;
/**
 * 游戏之类
 */
public enum GameChillType {
    KINGKONG(1, "kingKong")// 金刚
    , EGYPT(2, "egypt")//埃及
    , EAST(3, "east")//东方魔力
    , POWER_THUNDER(4, "thunder")//雷电之力
    , CLOWN_CIRCUS(5, "clown")//小丑马戏团
    , PIRATE(6, "pirate")//海盗
    , PILE_TOWER(7, "pile_tower")//炼金塔
    , DOLL(8, "doll")//娃娃机
    , ARCADE_MACHINE(9, "PRESENT")//娃娃机


    ;
    private int id;
    private String desc;

    GameChillType(int id, String desc) {
        this.id = id;
        this.desc = desc;
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

    public static GameChillType getGameChillType(int id) {

        for (GameChillType gameChillType : GameChillType.values()) {


            if (gameChillType.getId() == id) {
                return gameChillType;
            }


        }
        return null;
    }
}
