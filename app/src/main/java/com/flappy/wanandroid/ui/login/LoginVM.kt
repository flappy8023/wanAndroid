package com.flappy.wanandroid.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.repository.LoginRepository
import com.flappy.wanandroid.data.repository.MineRepository
import com.flappy.wanandroid.util.UserManager
import com.flappy.wanandroid.util.login.LoginIntercept

/**
 * @Author: luweiming
 * @Description:注册、登录
 * @Date: Created in 22:48 2022/10/27
 */
class LoginVM : BaseViewModel() {
    companion object {
        private const val TAG = "LoginVM"
    }

    private val _loginState: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val loginState: LiveData<Boolean>
        get() = _loginState

    fun login(username: String, pwd: String) {
        if (username.isBlank() or pwd.isBlank()) {
            _loginState.value = false
            return
        }
        launch {
            val loginResult = LoginRepository.login(username, pwd)
            //登录成功后请求并缓存用户信息,然后再发送登陆成功的状态
            if (loginResult.isSuccess) {
                getAndCacheUser()
            } else {
                _loginState.value = false
            }
        }
    }

    private suspend fun getAndCacheUser() {
        val result = MineRepository.getUserInfo()
        Log.d(
            TAG,
            "request userInfo:${result.isSuccess},${result.exceptionOrNull()?.localizedMessage}"
        )
        if (result.isSuccess) {
            //缓存用户信息
            UserManager.saveUserInfo(result.getOrNull())
            //缓存登录状态
            LoginIntercept.get().loginFinish()
        }
        _loginState.value = true

    }
}