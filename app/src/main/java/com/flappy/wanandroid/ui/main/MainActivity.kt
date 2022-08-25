package com.flappy.wanandroid.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.model.api.ApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //创建闪屏页
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
           val response= ApiManager.service.login("flappy8023", "lw1993315211")
            val res = ApiManager.service.getUserInfo()
        }
        GlobalScope.launch(Dispatchers.Main) {
            Log.d("fff","${Thread.currentThread().name} globalScope")
        }
        Log.d("fff","main")
    }
}