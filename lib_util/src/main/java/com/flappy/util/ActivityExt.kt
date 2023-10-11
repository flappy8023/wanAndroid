package com.flappy.util

import android.app.Activity
import android.os.Build
import androidx.core.view.WindowCompat

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
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = isLight
        }
    }
}