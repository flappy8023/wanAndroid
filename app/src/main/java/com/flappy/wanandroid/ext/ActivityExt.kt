package com.flappy.wanandroid.ext

import android.app.Activity
import android.os.Build
import android.view.View

/**
 * @Author: luweiming
 * @Description: Activity相关扩展方法
 * @Date: Created in 10:08 2022/11/10
 */

/**
 * 切换状态栏亮色模式
 *
 * @param isLight
 */
fun Activity.switchStatusBarLightMode(isLight: Boolean){
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
        this.window.decorView.systemUiVisibility = if(isLight)View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else View.SYSTEM_UI_FLAG_VISIBLE
    }
}