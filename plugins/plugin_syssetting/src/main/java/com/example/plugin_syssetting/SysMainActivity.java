package com.example.plugin_syssetting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * build文件已配置好，每次插件代码改动要生成jar包，点击右边Gradle对应插件的build或assembleDebug命令
 * 即可生成jar包到宿主app/assets/plugins/目录下
 */
public class SysMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_jump;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_main);
        bt_jump = (Button) findViewById(R.id.bt_jump);
        bt_jump.setOnClickListener(this);
        mContext = this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_jump:
                Intent intent = new Intent(mContext, EventServiceActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
