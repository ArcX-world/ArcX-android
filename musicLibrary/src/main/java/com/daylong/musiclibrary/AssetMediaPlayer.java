package com.daylong.musiclibrary;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;

import com.daylong.musiclibrary.emums.MediaPlayerType;
import com.daylong.musiclibrary.service.IPlay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class AssetMediaPlayer implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, IPlay {

    public MediaPlayer mediaPlayer; // 媒体播放器
    private AssetManager assetManager;
    private List<String> defaultMusicList = new ArrayList<>();
    //队列音效
    private LinkedBlockingQueue<String> blockingQueueMusic = new LinkedBlockingQueue<String>();
    private Set<String> notLoopingName = new HashSet<>();


    private String prefix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    // 初始化播放器
    public AssetMediaPlayer(Context context) {
        try {
            assetManager = context.getAssets();
            mediaPlayer = new MediaPlayer();
            // 设置播放类型 设置类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // 加载完毕回调
            mediaPlayer.setOnPreparedListener(this);
            //流媒体播放结束监听
            mediaPlayer.setOnCompletionListener(this);
            notLoopingName.add(MediaPlayerType.RANKING_MVP.getMusicName());
            notLoopingName.add(MediaPlayerType.PRIZE_WHEEL_GRAND.getMusicName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 每一秒触发一次

    }


    /**
     * 添加默认音乐 播放其他营销之后结束后重新播放 默认音效
     *
     * @param defaultMusicList
     */
    public void initBgMusic(String[] defaultMusicList) {
        defaultMusicList.clone();
        this.defaultMusicList.addAll(Arrays.asList(defaultMusicList));
    }


    /**
     * 播放
     */
    public void play() {
        //随机播放音乐
        if (TextUtils.equals(playMusicName, "start")) {
            return;
        }
        if (defaultMusicList != null && defaultMusicList.size() > 0) {
            int num = (int) (System.currentTimeMillis() % defaultMusicList.size());
            play(defaultMusicList.get(num));
        }


    }


    //判断是否正在播放中
    public boolean isPlay() {
        return mediaPlayer.isPlaying();
    }


    private float volume = 1.0f;


    private String playMusicName;

    /**
     * 播放音效
     */
    public void play(String musicName, float volume) {
        try {


            if (mediaPlayer.isPlaying() && playMusicName.equals(musicName)) {
                return;
            }
            playMusicName = musicName;
            this.volume = volume;
            //重置MediaPlayer至未初始化状态。
            mediaPlayer.reset();
            String musicPath;

            if (musicName.startsWith("sound_")) {
                musicPath = "music/" + musicName + ".mp3";
            } else {
                musicPath = "music/" + prefix + "/sound_" + prefix + "_" + musicName + ".mp3";

            }

            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(musicPath);
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.setVolume(volume, volume);
            //异步加载声音
            mediaPlayer.prepareAsync();
            // music/egypt/sound_egypt_start
            // music/egypt/sound_egypt_start.mp3
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放音效
     */

    @Override
    public void play(String playPath) {
        blockingQueueMusic.clear();
        try {
            String[] split = playPath.split(",");

            for (String s : split) {
                blockingQueueMusic.put(s);
            }
            if (blockingQueueMusic.size() > 0) {
                String take = blockingQueueMusic.take();
                play(take, 1.0f);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止音乐
     */
    @Override
    public void stop() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }
    }

    @Override
    public void destroy() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            //重置MediaPlayer至未初始化状态。
            mediaPlayer.reset();
            //回收流媒体
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    /**
     * 是否循环
     */
    private boolean isLoopPlay = true;

    /**
     * 是否循环播放··
     *
     * @param loopPlay
     */
    public void setLoopPlay(boolean loopPlay) {
        isLoopPlay = loopPlay;
    }


    //加载完毕声音回调
    @Override
    public void onPrepared(MediaPlayer mp) {
        //加载成功


        int duration = mp.getDuration();


        mp.setLooping(!notLoopingName.contains(playMusicName) && duration > 7000);

        mp.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {


        if (blockingQueueMusic.size() > 0) {
            String take = null;
            try {
                take = blockingQueueMusic.take();
                if (take != null) {
                    play(take, 1.0f);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            if (isLoopPlay) {
                if (defaultMusicList != null && defaultMusicList.size() > 0) {
                    int num = (int) (System.currentTimeMillis() % defaultMusicList.size());
                    play(defaultMusicList.get(num), 1.0f);

                }
            }
        }
    }


    /**
     * 播放音乐
     */
    public void playMusic() {
        mediaPlayer.start();

    }


    public void stopAndSwBg() {
        stop();
    }


    public void exit() {
        stop();
        defaultMusicList.clear();
        blockingQueueMusic.clear();
    }
}
