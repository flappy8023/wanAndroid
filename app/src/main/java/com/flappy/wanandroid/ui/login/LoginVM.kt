package com.flappy.wanandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.UserInfo
import com.flappy.wanandroid.data.repository.LoginRepository
import com.flappy.wanandroid.data.repository.MineRepository
import com.flappy.wanandroid.util.UserManager
import com.jeremyliao.liveeventbus.LiveEventBus

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
            //登录成功后请求并缓存用户信息
            if (loginResult.isSuccess) {
                getAndCacheUser()
            }
        }
    }

    private fun getAndCacheUser() {
        launch {
            val result = MineRepository.getUserInfo()
            if (result.isSuccess) {
                //缓存用户信息
                UserManager.saveUserInfo(result.getOrNull())
                LiveEventBus.get<UserInfo>(UserManager.KEY_USER_INFO)
                    .post(result.getOrNull()?.userInfo)
            }
        }
    }
}