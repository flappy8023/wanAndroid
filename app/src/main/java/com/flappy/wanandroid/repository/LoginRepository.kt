package com.flappy.wanandroid.repository

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:47 2022/10/28
 */
object LoginRepository {
    suspend fun login(name: String, pwd: String) =
        safeApiCall { ApiManager.service.login(name, pwd) }
}