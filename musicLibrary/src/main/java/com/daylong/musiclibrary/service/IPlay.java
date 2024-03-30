package com.daylong.musiclibrary.service;

public interface IPlay {

    /**
     * 设置初始化背景
     *
     * @param bgMusic 背景音乐
     */
    void initBgMusic(String[] bgMusic);

    void play();

    void play(String playPath);
    void stop();
    void destroy();
}
