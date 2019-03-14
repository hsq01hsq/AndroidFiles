package com.example.mymusicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service {
    public MediaPlayer mediaPlayer;

    public MusicService() {
        mediaPlayer = new MediaPlayer();
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/music.mp3";
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }
    //通过Binder来保持Activity和Service的通信
    public MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void play() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }
}
