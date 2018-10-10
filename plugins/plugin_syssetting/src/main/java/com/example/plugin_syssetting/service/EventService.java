package com.example.plugin_syssetting.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.plugin_syssetting.eventbus.MessageEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 创建日期：2018/9/10
 * 作者:baiyang
 */
public class EventService extends Service {
    private static final String TAG = "EventService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: onStartCommand");
        String value = intent.getStringExtra("value");
        Log.e(TAG, "onStartCommand: value:"+value);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        Log.e(TAG, "onCreate: EventService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e(TAG, "onDestroy: EventService");
    }

    /**
     * 外面调用的方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMenthod(MessageEntity msg) {
        if (msg.what == 100) {
            Log.e(TAG, "eventMenthod: " + "EventService调用方法成功");
        }
    }
}
