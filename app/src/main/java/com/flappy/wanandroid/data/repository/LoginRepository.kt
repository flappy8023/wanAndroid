package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.util.safeCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:47 2022/10/28
 */
object LoginRepository {
    suspend fun login(name: String, pwd: String) =
        safeCall { ApiManager.service.login(name, pwd) }
}