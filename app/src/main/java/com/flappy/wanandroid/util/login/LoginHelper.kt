package com.flappy.wanandroid.util.login

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.NavMainDirections
import com.flappy.wanandroid.util.UserManager
import com.flappy.wanandroid.util.dataStore
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.coroutines.Dispatchers
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
    private val loginState = LiveEventBus.get<Boolean>(KEY_IS_LOGIN)

    fun observerLogin(owner: LifecycleOwner, onLogin: () -> Unit, onLogout: (() -> Unit)? = null) {
        loginState.observeSticky(owner) {
            if (it) {
                onLogin.invoke()
            } else {
                onLogout?.invoke()
            }
        }
    }

    fun isLogin() = runBlocking(Dispatchers.IO) {
        MyApp.app.dataStore.data.map {
            it[booleanPreferencesKey(KEY_IS_LOGIN)] ?: false
        }.first()
    }

    fun setLoginSuccess() = runBlocking(Dispatchers.IO) {
        MyApp.app.dataStore.edit {
            it[booleanPreferencesKey(KEY_IS_LOGIN)] = true
        }
        loginState.post(true)
    }

    fun logout() = runBlocking {
        UserManager.deleteUserInfo()
        MyApp.app.dataStore.edit {
            it[booleanPreferencesKey(KEY_IS_LOGIN)] = false
        }
        loginState.post(false)
    }

    fun goLogin(fragment: Fragment) =
        fragment.findNavController().navigate(NavMainDirections.goLogin())

}