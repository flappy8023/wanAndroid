package com.flappy.wanandroid

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:58 2022/8/22
 */
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
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}