package com.daylong.musiclibrary.mrg;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

/**
 * 音乐管理
 */
public class SoundPoolManager {
    private static SoundPoolManager soundPoolManager;
    private static SoundPool soundPool;
    private static Map<Integer, Integer> soundEffectMap; //保存资源 ID  关联在家音效的Id
    private static Map<Integer, Integer> soundEffectPlayIdMap; //播放ID  关联音效缓存资源ID


    public static synchronized SoundPoolManager getInstance() {
        if (soundPoolManager == null) {
            synchronized (SoundPoolManager.class) {
                soundEffectPlayIdMap = new HashMap<>();
                soundEffectMap = new HashMap<>();
                soundPoolManager = new SoundPoolManager();
                crateSoundPool();
            }
        }
        return soundPoolManager;
    }

    //创建播放器
    private static void crateSoundPool() {
        SoundPool.Builder builder = new SoundPool.Builder();
        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
        builder.setAudioAttributes(attrBuilder.build());
        //最多同时可以播放路数
        builder.setMaxStreams(10);
        soundPool = builder.build();
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundEffectPlayIdMap.put(sampleId, soundPool.play(sampleId, 1, 1, 1, 0, 1));

            }

        });
    }

    private static Context mContext;


    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 播放音效
     *
     * @param rawId
     */
    public void play(int rawId) {
        play(rawId, false);
    }

    /**
     * 播放音效
     *
     * @param rawId
     */
    public void play(int rawId, boolean isStop) {
        if (!isOpenMusic()) {
            return;
        }
        if (soundEffectMap.containsKey(rawId)) {
            Integer musicId = soundEffectMap.get(rawId);
            if (musicId != null) {
                //停止当前音效

                if (soundEffectPlayIdMap.containsKey(musicId)) {
                    Integer playId = soundEffectPlayIdMap.get(musicId);
                    if (playId != null && isStop) {
                        soundPool.stop(playId);
                    }
                }
                soundEffectPlayIdMap.put(musicId, soundPool.play(musicId, 1, 1, 1, 0, 1));
            }
        } else {
            int musicId = getMusicId(rawId);
            soundEffectMap.put(rawId, musicId);
        }
    }


    /**
     * 获取音乐
     *
     * @param rawId
     * @return
     */
    private int getMusicId(int rawId) {
        if (soundPool == null) {
            crateSoundPool();
        }
        return soundPool.load(mContext, rawId, 1);
    }

    /**
     * 停止播放音效
     *
     * @param rawId
     */
    public void stop(int rawId) {
        if (soundEffectMap.containsKey(rawId)) {
            Integer musicId = soundEffectMap.get(rawId);
            if (musicId != null) {
                Integer playId = soundEffectPlayIdMap.get(musicId);
                if (playId != null) {
                    soundPool.stop(playId);

                }
            }
        }
    }


    public void closeSoundEffectPlayIdMap() {
        //清除
        soundPool.release();
        soundEffectPlayIdMap.clear();
    }


    private boolean sound;

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isOpenMusic() {
        return sound;
    }
}
