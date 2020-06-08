package com.cymjoe.classes0608;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cymjoe.stander.ActivityInterface;

import java.lang.reflect.Constructor;

/**
 * 代理activity 代理/占位 插件里面的Activity
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getClassLoader();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //真正的加载插件里的activity
        String className = getIntent().getStringExtra("className");
        try {
            Class mPluginActivityClass = getClassLoader().loadClass(className);
//实例化插件包里的activity
            Constructor constructor =
                    mPluginActivityClass.getConstructor(new Class[]{});
            Object mPluginActivity = constructor.newInstance(new Object[]{});
            ActivityInterface activityInterface = (ActivityInterface) mPluginActivity;

            //把宿主环境注入给插件
            activityInterface.insertAppContext(this);
            Bundle bundle = new Bundle();
            bundle.putString("appName", "我是宿主传递过来的信息");
            //执行插件里面的onCreate
            activityInterface.onCreate(bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent proxyIntent = new Intent(this, ProxyActivity.class);
        proxyIntent.putExtra("className", className);
        super.startActivity(proxyIntent);

    }
}
