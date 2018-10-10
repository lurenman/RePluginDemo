package com.example.dell.plugindemo;

import android.app.ActivityManager;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.component.service.server.IPluginServiceServer;
import com.qihoo360.replugin.component.service.server.PluginServiceServer;

/**
 * 关于插件请参考官网
 * https://github.com/Qihoo360/RePlugin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_jump;
    private Button bt_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_jump = (Button) findViewById(R.id.bt_jump);
        bt_jump.setOnClickListener(this);
        bt_click = (Button) findViewById(R.id.bt_click);
        bt_click.setOnClickListener(this);
        RePlugin.preload("plugin_syssetting");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_jump:
                RePlugin.startActivity(MainActivity.this, RePlugin.createIntent("com.example.plugin_syssetting", "com.example.plugin_syssetting.SysMainActivity"));
                break;
            case R.id.bt_click:
//                if (isServiceRunning()) {
//                    Toast.makeText(this, "服务正在运行", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "服务沒有在运行", Toast.LENGTH_SHORT).show();
//                }
                break;
            default:
                break;
        }
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
