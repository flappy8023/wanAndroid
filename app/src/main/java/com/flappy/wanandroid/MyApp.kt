package com.flappy.wanandroid

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.flappy.wanandroid.data.db.WanDB
import com.flappy.wanandroid.util.DarkModeUtil
import com.flappy.webview.WebViewPool
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import dagger.hilt.android.HiltAndroidApp


/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:58 2022/8/22
 */
@HiltAndroidApp
class MyApp : Application() {
    companion object {
        lateinit var app: MyApp
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        app = this
    }

    override fun onCreate() {
        super.onCreate()
        //x5内核dex2oat优化方案开启
        val map = mutableMapOf<String, Any>()
        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(map)
        QbSdk.initX5Environment(app, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {

            }

            //x5内核，依赖动态下发，下发完成前使用系统内核
            override fun onViewInitFinished(isX5: Boolean) {
            }
        })
        WanDB.init(this)
        //webview池初始化
        WebViewPool.get().init(app)
        val darkModeValue =
            PreferenceManager.getDefaultSharedPreferences(app).getString("dark_mode", "auto")
        DarkModeUtil.initDarkMode(darkModeValue)
    }
}