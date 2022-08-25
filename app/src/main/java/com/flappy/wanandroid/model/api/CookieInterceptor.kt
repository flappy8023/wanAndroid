package com.flappy.wanandroid.model.api

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.util.dataStore
import com.orhanobut.logger.Logger
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: luweiming
 * @Description: 获取cookie以及持久化保存cookie
 * @Date: Created in 21:55 2022/8/24
 */
class CookieInterceptor : Interceptor {
    private val TAG = "CookieInterceptor"
    val dataStore by lazy { MyApp.app.dataStore }
    private val COOKIE_NAME = "Cookie"
    private val SET_COOKIE_KEY = "set-cookie"
    private val URL_LOGIN = "user/login"
    private val URL_REGISTER = "user/register"

    @OptIn(DelicateCoroutinesApi::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val url = request.url.toString()
        val host = request.url.host
        if ((url.contains(URL_LOGIN) || url.contains(URL_REGISTER))
        ) {
            val response = chain.proceed(request)
            if (response.headers(
                    SET_COOKIE_KEY
                ).isNotEmpty()
            ) {
                val cookies = response.headers(SET_COOKIE_KEY)
                val cookie = encodeCookie(cookies)
                //dataStore存储cookie
                GlobalScope.launch {
                    dataStore.edit {
                        it[stringPreferencesKey(host)] = cookie
                        it[stringPreferencesKey(url)] = cookie
                    }
                }
            }
            return response
        } else if (host.isNotEmpty()) {
            //dataStore读取cookie
            var cookie = ""
            runBlocking {
                cookie = dataStore.data.map { it[stringPreferencesKey(host)] }.first().toString()
            }
            Logger.d("get cookie from dataStore is $cookie")
            builder.addHeader(COOKIE_NAME, cookie)
            return chain.proceed(builder.build())
        }
        return chain.proceed(request)
    }

    private fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies.map { cookie ->
            cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }.forEach { item ->
            item.forEach { set.add(it) }
        }
        set.forEach { sb.append(it).append(";") }
        return if (sb.endsWith(";")) sb.deleteCharAt(sb.length - 1).toString() else sb.toString()
    }
}