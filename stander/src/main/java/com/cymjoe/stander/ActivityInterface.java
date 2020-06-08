package com.cymjoe.stander;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface ActivityInterface {

    /**
     * 把宿主的环境给插件
     *
     * @param appActivity
     */
    void insertAppContext(Activity appActivity);

    //生命周期方法
    void onCreate(Bundle savedInstanceState);

    //生命周期方法
    void onStart();

    //生命周期方法
    void onResume();

    //生命周期方法
    void onPause();

    //生命周期方法
    void onStop();

    //生命周期方法
    void onDestroy();

    //生命周期方法
    void onRestart();


    void onNewIntent(Intent intent);


    void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState);

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
}
