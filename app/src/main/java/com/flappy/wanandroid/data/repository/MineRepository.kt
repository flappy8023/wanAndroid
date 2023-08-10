package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.db.RoomDB
import com.flappy.wanandroid.util.safeApiCall
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:00 2022/10/31
 */
class MineRepository @Inject constructor(
    private val apiService: ApiService,
    private val roomDB: RoomDB
) {
    suspend fun getUserInfo() = safeApiCall { apiService.getUserInfo() }

    suspend fun logout() = safeApiCall { apiService.logout() }

    suspend fun getReadHistoryList(offset: Int, count: Int) =
        roomDB.readHistoryDao().getAll(offset, count)
}