package com.flappy.wanandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.repository.LoginRepository

/**
 * @Author: luweiming
 * @Description:注册、登录
 * @Date: Created in 22:48 2022/10/27
 */
class LoginVM : BaseViewModel() {
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
            _loginState.value = loginResult.isSuccess
        }
    }
}