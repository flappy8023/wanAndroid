package com.flappy.wanandroid.api

import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.config.HttpConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @Author: luweiming
 * @Description: api管理类，提供service实例
 * @Date: Created in 19:54 2022/8/20
 */
object ApiManager {
    /**
     * 配置OkHttp客户端
     */
    private val okHttpClient by lazy {
        val cacheDir = File(MyApp.app.externalCacheDir,HttpConfig.HTTP_CACHE_PATH)
        OkHttpClient.Builder()
            .cache(Cache(cacheDir,HttpConfig.HTTP_CACHE_SIZE))
            .connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(CookieInterceptor())
            .addInterceptor(CacheInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}