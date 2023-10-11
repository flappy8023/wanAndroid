package com.flappy.wanandroid.util

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.flappy.util.JsonUtil
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.data.model.UserInfoData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * @Author: luweiming
 * @Description:用户信息、状态管理
 * @Date: Created in 15:28 2022/10/31
 */
object UserManager {
    private const val KEY_CUR_USER = "cur_user"
    const val KEY_USER_INFO = "userInfo"
    private val dataStore by lazy { MyApp.app.dataStore }

    /**
     * 内存缓存用户信息
     */
    private var curUser: UserInfoData? = null


    fun getCurUser(): UserInfoData? {
        if (null != curUser)
            return curUser
        val userInfoString = runBlocking {

            dataStore.data.map { it[stringPreferencesKey(KEY_CUR_USER)] }.first()
        }

        curUser = userInfoString?.let {
            JsonUtil.fromJsonString(it, UserInfoData::class.java)
        }
        return curUser
    }

    fun saveUserInfo(userInfo: UserInfoData?) {
        curUser = userInfo
        if (null == userInfo) return
        runBlocking {

            dataStore.edit {
                it[stringPreferencesKey(KEY_CUR_USER)] =
                    JsonUtil.toJsonString(userInfo)
            }
        }
    }

    fun deleteUserInfo() {
        curUser = null
        runBlocking {
            dataStore.edit {
                it[stringPreferencesKey(KEY_CUR_USER)] = ""
            }
        }

    }

}