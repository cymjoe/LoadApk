package com.cymjoe.plugin_package;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PluginActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        Toast.makeText(appActivity, "我是插件" + appActivity.getIntent().getStringExtra("className"), Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("jasdkljask");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(appActivity, MainActivity2.class));
            }
        });
    }


}
