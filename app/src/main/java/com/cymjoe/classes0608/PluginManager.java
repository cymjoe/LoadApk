package com.cymjoe.classes0608;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static final String TAG = PluginManager.class.getName();
    private Context context;
    private static PluginManager instance;
    private DexClassLoader dexClassLoader;
    private Resources resources;

    public static PluginManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager(context);
                }
            }

        }
        return instance;
    }

    public PluginManager(Context context) {
        this.context = context;
    }

    /**
     * 加载插件
     */
    public void loadPlugin(String apkName) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + apkName + ".apk");
            if (!file.exists()) {
                Log.d(TAG, "插件包不存在");
                return;
            }
            String pluginPath = file.getAbsolutePath();

            /**
             * 加载插件里的class
             */

            //dexClassLoader 需要一个缓存目录
            File cacheDir = context.getDir(apkName + "Dir", Context.MODE_PRIVATE);

            //Dex类加载器
            dexClassLoader = new DexClassLoader(pluginPath, cacheDir.getAbsolutePath(), null, context.getClassLoader());


            /**
             * 加载插件里面的layout
             */

            //加载资源
//            AssetManager manager = context.getAssets();
            AssetManager manager = AssetManager.class.newInstance();

            //把插件包的路径添加进去
            Method addAssetPath = manager.getClass().getMethod("addAssetPath", String.class);//不是真正的类型 类 类型
            addAssetPath.invoke(manager, pluginPath);//插件包的路径

            Resources r = context.getResources();

            //特殊的Resources 加载插件里的资源的Resources
            resources = new Resources(manager, r.getDisplayMetrics(), r.getConfiguration());//参数2 3  资源配置信息


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClassLoader getClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }
}
