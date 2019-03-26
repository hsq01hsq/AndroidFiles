package com.example.musicservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getName();
    private Button btnStart, btnStop, btnBind, btnUnbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"MainActivity", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MainActivity");
        findViewById();
    }

    private void findViewById() {
        btnStart = findViewById(R.id.startMusic);
        btnStop = findViewById(R.id.stopMusic);
        btnBind = findViewById(R.id.bindMusic);
        btnUnbind = findViewById(R.id.unbindMusic);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        switch (v.getId()) {
            case R.id.startMusic:
                //开始服务
                startService(intent);
                break;
            case R.id.stopMusic:
                //停止服务
                stopService(intent);
                break;
            case R.id.bindMusic:
                //绑定服务
                bindService(intent,conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindMusic:
                //解绑服务
                unbindService(conn);
                break;
            default:
                break;
        }
    }

    //定义服务链接对象
    final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this,"MainActivity onServiceConnected",Toast.LENGTH_SHORT).show();
            Log.e(TAG, "MainActivity onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this,"MainActivity onServiceDisconnected",Toast.LENGTH_SHORT).show();
            Log.e(TAG, "MainActivity onServiceDisconnected");
        }
    };
}
