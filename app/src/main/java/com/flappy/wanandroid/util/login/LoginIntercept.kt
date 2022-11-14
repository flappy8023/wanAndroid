package com.flappy.wanandroid.util.login

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:登录拦截器
 * @Date: Created in 16:01 2022/11/14
 */
class LoginIntercept private constructor() : CoroutineScope by MainScope(),
    DefaultLifecycleObserver {
    companion object {
        private var instance: LoginIntercept? = null
            get() {
                if (null == field) {
                    field = LoginIntercept()
                }
                return field
            }

        fun get() = instance!!
    }

    private val channel: Channel<Boolean> = Channel()
    fun checkLogin(loginAction: () -> Unit, nextAction: () -> Unit) {
        launch {
            if (LoginHelper.isLogin()) {
                nextAction()
                return@launch
            }
            loginAction()
            val loginResult = channel.receive()
            if (loginResult) {
                nextAction()
            }
        }
    }

    fun loginFinish() {
        launch {
            channel.send(true)
            LoginHelper.setLoginSuccess()
        }
    }


    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        channel.cancel()
        cancel()
    }
}