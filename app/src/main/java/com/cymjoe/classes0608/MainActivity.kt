package com.cymjoe.classes0608

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 加载插件
     */
    fun loadApkClick(view: View) {
        PluginManager.getInstance(this).loadPlugin("b")


    }
//
    /**
     * 启动插件
     */
    fun toApk(view: View) {
        val file = File(
            Environment.getExternalStorageDirectory()
                .toString() + File.separator + "b" + ".apk"
        )
        val path = file.absolutePath
        Log.d("OOASDAS", path)

        //获取插件包里面的activity
        val packageManager = packageManager
        val packageInfo =
            packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)
        val activityInfo = packageInfo.activities[0]
        val intent = Intent(this, ProxyActivity::class.java)
        intent.putExtra("className",activityInfo.name)
        startActivity(intent)

    }


}
