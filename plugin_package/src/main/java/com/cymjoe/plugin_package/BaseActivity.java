package com.cymjoe.plugin_package;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cymjoe.stander.ActivityInterface;

@SuppressLint("MissingSuperCall")
public class BaseActivity extends AppCompatActivity implements ActivityInterface {
    //宿主的环境
    public Activity appActivity;

    @Override
    public void insertAppContext(Activity appActivity) {
        this.appActivity = appActivity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }

    public void setContentView(int resId) {
        appActivity.setContentView(resId);
    }

    public View findViewById(int resId) {
        return appActivity.findViewById(resId);
    }



    @Override
    public void startActivity(Intent intent) {
        Intent intent1 = new Intent();
        intent1.putExtra("className", intent.getComponent().getClassName());
        appActivity.startActivity(intent1);
    }
}
