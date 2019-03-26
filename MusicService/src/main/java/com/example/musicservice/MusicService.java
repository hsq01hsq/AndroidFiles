package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;

public class MusicService extends Service {
    //为日志工具设置标签
    private static final String TAG = MusicService.class.getName();
    //定义音乐播放器变量
    private MediaPlayer mPlayer;

    //该服务不存在需要被创建时被调用，不管startService()还是bindService()都会启动该方法
    @Override
    public void onCreate() {
        Toast.makeText(this,"MusicService onCreate()",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicService onCreate()");
        //mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        String filePath = Environment.getExternalStorageDirectory().getAbsoluteFile()+"/data/music.mp3";
        try {
            mPlayer.setDataSource(filePath);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置可以重复播放
        mPlayer.setLooping(true);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"MusicService onStartCommand()",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicService onStartCommand()");
        mPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"MusicService onDestroy()",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicService onDestroy()");
        mPlayer.stop();
        super.onDestroy();
    }

    //其他对象通过bindService方法通知该Service时该方法被调用
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"MusicService onBind()",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicService onBind()");
        mPlayer.start();
        return null;
    }
    //其他对象通过unbindService方法通知该Service是该方法被调用

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"MusicService onUnBind()",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicService onUnBind()");
        mPlayer.stop();
        return super.onUnbind(intent);
    }
}
