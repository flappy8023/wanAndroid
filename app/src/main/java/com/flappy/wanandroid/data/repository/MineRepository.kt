package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:00 2022/10/31
 */
object MineRepository {
    suspend fun getUserInfo() = safeApiCall { ApiManager.service.getUserInfo() }
}