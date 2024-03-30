package com.daylong.musiclibrary.emums;

import android.text.TextUtils;

import com.daylong.musiclibrary.R;
import com.daylong.musiclibrary.mrg.MediaPlayerMrg;
import com.daylong.musiclibrary.mrg.SoundPoolManager;


public enum MediaPlayerType {
    FREE_GAME(1, "free_game", "免费游戏")//
    , GRM(2, "gem_01,gem_02", "宝石")//
    , PRIZE_WHEEL_GRAND(3, "sound_game_award_wheel", "大奖转盘-grand奖池")//
    , PRIZE_WHEEL_MAJOR(4, "sound_game_award_wheel", "大奖转盘-major奖池")//
    , PRIZE_WHEEL_MINOR(5, "sound_game_award_wheel", "大奖转盘-minor奖池")//
    , DRAGON_BALL(6, R.raw.sound_game_dragon_bonus_show, "显示龙珠")//
    , AGYPT_OPEN_BOX(8, "guess_box", "埃及开箱子")////
    , GOSSIP(9, "copper", "八卦")////
    , COPPER_FULL(10, R.raw.sound_east_copper, "铜钱集满")////
    , HERO_BATTLE(11, "gossip", "三国战斗\n")////

    , THUNDER(12, "thunder_01,thunder_02", "闪电收集")////
    , THUNDER_UP(13, R.raw.sound_thunder_thunder_up, "闪电收集")////

    , THUNDER_COLLECT(17, R.raw.sound_thunder_collect, "闪电收集")////

    //Skill
    , SKILL_FREE_COIN(6000, "sound_skill_free_coin_start,sound_skill_free_coin_bgm", "技能投币")//
    , SKILL_FREE_TIME(6000, "sound_skill_free_time_start,sound_skill_free_time_bgm", "时间")//
    //---------------------
    , SHOW_BALL_MALI(6000, R.raw.sound_game_dragon_train_show, "龙珠显示玛丽")//
    , SHOW_BALL_MALI_RUN(6001, R.raw.sound_game_dragon_train_run, "玛丽转圈")//
    , SHOW_BALL_MALI_STOP(6002, R.raw.sound_game_dragon_train_stop, "玛丽转圈")//

    // 排行榜
    , RANKING_MVP(6002, "sound_game_ranking_end_mvp", "mvp")//

    //免费游戏
    , KING_FREE_GAME(100001, "free_game", "金刚")//
    , EGYPT_FREE_GAME(200001, "free_game", "埃及")//
    , EAST_FREE_GAME(300001, "free_game_01,free_game_02", "东方魔力")//
    , THUNDER_FREE_GAME(500001, "free_game_01,free_game_02", "雷电")//


    //
    ;

    private int id;
    private String musicName;
    private int rawId;
    private String desc;


    public static void playMusic(int id) {
        MediaPlayerType productAwardType = null;
        for (MediaPlayerType value : MediaPlayerType.values()) {
            if (value.getId() == id) {
                productAwardType = value;
                break;
            }
        }
        if (productAwardType != null) {
            productAwardType.play();
        }

    }

    MediaPlayerType(int id, String musicName, String desc) {
        this.id = id;
        this.musicName = musicName;
        this.desc = desc;
    }

    MediaPlayerType(int id, int rawId, String desc) {
        this.id = id;
        this.rawId = rawId;
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

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public void play() {

        //不等于空

        if (!TextUtils.isEmpty(musicName)) {
            MediaPlayerMrg.getInstance().play(musicName);
        } else {
            SoundPoolManager.getInstance().play(getRawId());
        }
    }

    public void stop() {
        //不等于空
        if (!TextUtils.isEmpty(musicName)) {
            MediaPlayerMrg.getInstance().play(musicName);
        } else {
            SoundPoolManager.getInstance().stop(getRawId());
        }
    }
}
