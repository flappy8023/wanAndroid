package com.flappy.wanandroid.util

import android.content.Context

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:24 2022/12/16
 */
object AppUtil {
    fun getVersionName(context: Context): String =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName

    fun getVersionCode(context: Context): Int =
        context.packageManager.getPackageInfo(context.packageName, 0).versionCode

}