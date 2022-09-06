package com.flappy.wanandroid.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.net.InetAddress
import java.net.UnknownHostException


/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:18 2022/9/6
 */
object NetUtil {
    private const val TAG = "NetUtil"
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }

    fun isInternetAvailable(): Boolean {
        try {
            val address: InetAddress = InetAddress.getByName("www.baidu.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {
            Log.e(TAG, e.toString())
        }
        return false
    }
}