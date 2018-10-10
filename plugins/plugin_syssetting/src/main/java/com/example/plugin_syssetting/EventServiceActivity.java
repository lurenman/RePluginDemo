package com.example.plugin_syssetting;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.plugin_syssetting.eventbus.MessageEntity;
import com.example.plugin_syssetting.service.EventService;
import com.example.plugin_syssetting.util.SPUtils;
import com.qihoo360.replugin.loader.s.PluginServiceClient;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建日期：2018/9/10
 * 作者:baiyang
 */
public class EventServiceActivity extends AppCompatActivity {
    private static final String TAG = "EventServiceActivity";
    private Button bt_start;
    private Button bt_stop;
    private Button bt_event;
    private Button bt_isrun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventservice);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventServiceActivity.this, EventService.class);
                intent.putExtra("value", "I am lurenman");
                startService(intent);
            }
        });
        bt_stop = (Button) findViewById(R.id.bt_stop);
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventServiceActivity.this, EventService.class);
                stopService(intent);
            }
        });
        bt_event = (Button) findViewById(R.id.bt_event);
        bt_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过EventBus调用service方法
                MessageEntity messageEntity = MessageEntity.obtianMessage();
                messageEntity.what = 100;
                EventBus.getDefault().post(messageEntity);
            }
        });
        bt_isrun = (Button) findViewById(R.id.bt_isrun);
        bt_isrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceRunning()){
                    Toast.makeText(EventServiceActivity.this, "服务正在运行", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EventServiceActivity.this, "服务沒有在运行", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState:---------- ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        SPUtils.put(this, "onRestoreInstanceState", time);
        boolean serviceRunning = isServiceRunning();
        if (!serviceRunning){
            Intent intent = new Intent(EventServiceActivity.this, EventService.class);
            intent.putExtra("value", "I am lurenman");
            startService(intent);
            Toast.makeText(this, "重启service", Toast.LENGTH_SHORT).show();
            MessageEntity messageEntity = MessageEntity.obtianMessage();
            messageEntity.what = 100;
            EventBus.getDefault().post(messageEntity);
        }
        Log.e(TAG, "onRestoreInstanceState: -----------");
    }
    /**
     * 此运行方法在RePlugin中无效 ，插件源码中维护了一个私有的服务集合
     * @return
     */
    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.example.plugin_syssetting.service.EventService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
