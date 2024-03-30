package com.daylong.musiclibrary.emums;

import com.daylong.musiclibrary.R;
import com.daylong.musiclibrary.mrg.SoundPoolManager;

public enum SoundPoolType {
    PUSH_COIN(R.raw.sound_game_pusher_slot, "push_icon") //
    , EAST_PUSH_COIN(R.raw.sound_east_push_coin, "east_push_coin") //
    , GET_COIN(R.raw.sound_game_pusher_get_coin, "getCoin") //
    , COMBO_GOOD_JOB(R.raw.sound_game_combo_score_1, "ic_goodjob")//
    , COMBO_WELL_DONE(R.raw.sound_game_combo_score_2, "ic_welldone")//
    , COMBO_PERFECT(R.raw.sound_game_combo_score_3, "ic_perfect")//
    , COMBO_AMAZING(R.raw.sound_game_combo_score_4, "ic_amazing")//
    , COMBO_INCREDIBLE(R.raw.sound_game_combo_score_5, "ic_incredible")//
    , AWARD_NORMAL(R.raw.sound_game_award_normal, "普通獎")//
    , ALARM_BELL(R.raw.sound_game_award_alarm_bell, "其他獎勵")//
    , GAME_AUTO_START(R.raw.sound_game_auto_start, "自动")//
    , GAME_AUTO_STOP(R.raw.sound_game_auto_stop, "取消自动")//
    , GAME_TIMEOUT(R.raw.sound_game_timeout, "倒计时")//
    , SKILL_GET_TIME(R.raw.sound_skill_free_time_get, "获得技能时间")//
    , SKILL_GET_COIN(R.raw.sound_skill_free_coin_get, "获得技能币")//
    , RANKING_OPEN(R.raw.sound_game_ranking_open, "开启")//
    , RANKING_COLOSE(R.raw.sound_game_ranking_close, "关闭")//
    , RANKING_WIN(R.raw.sound_game_ranking_win, "中奖")//
    , RANKING_END_CLIENT(R.raw.sound_game_ranking_end_client, "结束排行榜等待开始")//
    , BIG_WIN(R.raw.sound_thunder_big_win, "1")//
    , HUGE_WIN(R.raw.sound_thunder_huge_win, "2")//
    , MEGA_WIN(R.raw.sound_thunder_mega_win, "3")//
    , COLLECT(R.raw.sound_bonus_collect, "3")//
    , CARD_HAPPY_CARD_FLIP(R.raw.sound_fun_card_flip, "翻转")//
    , CARD_HAPPY_CARD_MERGE(R.raw.sound_fun_card_merge, "合成")//

    , CARD_MIX_CARD(R.raw.sound_mix_card_mixing, "合成")//
    , CARD_Mix_CARD_CONGRATS(R.raw.sound_mix_card_congrats, "奖励")//
    //娃娃机
    , DOLL_WIN(R.raw.sound_doll_success, "抓抓娃娃")
    , DOLL_CATCH(R.raw.sound_doll_catch, "抓抓娃娃")
    , DOLL_MOVE(R.raw.sound_doll_move, "抓抓娃娃")

    // 大逃杀
    , KILLER_ATTACK(R.raw.sound_killer_attack, "攻击")
    , KILLER_FAIL(R.raw.sound_killer_fail, "失败")
    , KILLER_SUC(R.raw.sound_killer_suc, "成功")
    ,KILLER_WARN(R.raw.sound_killer_warn, "警告")
    ,KILLER_TIME(R.raw.sound_killer_time, "倒计时")
    //
    ;


    public static SoundPoolType getSoundPoolTypeByName(String name) {

        for (SoundPoolType soundPoolType : SoundPoolType.values()) {


            if (soundPoolType.getDesc().equals(name)) {
                return soundPoolType;
            }

        }

        return null;
    }

    private int rawId;
    private String desc;

    SoundPoolType(int rawId, String desc) {
        this.rawId = rawId;
        this.desc = desc;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public void play() {
        SoundPoolManager.getInstance().play(getRawId());
    }

    public void playNotStop() {
        SoundPoolManager.getInstance().play(getRawId(), false);
    }

    public void stop() {
        SoundPoolManager.getInstance().stop(getRawId());
    }
}
