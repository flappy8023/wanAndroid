package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.util.safeCall
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:47 2022/10/28
 */
class LoginRepository @Inject constructor(val apiService: ApiService) {
    suspend fun login(name: String, pwd: String) =
        safeCall { apiService.login(name, pwd) }
}