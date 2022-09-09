package com.flappy.wanandroid.api

import android.util.Log
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.util.NetUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:07 2022/9/6
 */
class CacheInterceptor : Interceptor {
    companion object {
        private const val TAG = "CacheInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(TAG, "currentTread:${Thread.currentThread().name}")
        if (!NetUtil.isNetworkAvailable(MyApp.app)) {
            request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (!NetUtil.isNetworkAvailable(MyApp.app)) {
            //一小时
            val maxAge = 60 * 60
            response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        } else {
            //4周
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
        return response
    }
}