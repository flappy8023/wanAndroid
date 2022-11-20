package com.flappy.wanandroid.util.login

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.NavMainDirections
import com.flappy.wanandroid.ext.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:23 2022/11/14
 */
object LoginHelper {
    const val KEY_IS_LOGIN = "is_login"


    fun isLogin() = runBlocking {
        MyApp.app.dataStore.data.map {
            it[booleanPreferencesKey(KEY_IS_LOGIN)] ?: false
        }.first()
    }

    fun setLoginSuccess() = runBlocking {
        MyApp.app.dataStore.edit {
            it[booleanPreferencesKey(KEY_IS_LOGIN)] = true
        }
    }

    fun goLogin(fragment: Fragment) =
        fragment.findNavController().navigate(NavMainDirections.goLogin())
}