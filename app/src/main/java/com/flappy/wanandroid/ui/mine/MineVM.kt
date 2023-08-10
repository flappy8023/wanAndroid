package com.flappy.wanandroid.ui.mine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.UserInfoData
import com.flappy.wanandroid.data.repository.MineRepository
import com.flappy.wanandroid.util.UserManager
import com.flappy.wanandroid.util.login.LoginHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:我的页面对应ViewModel
 * @Date: Created in 22:19 2022/10/17
 */
@HiltViewModel
class MineVM @Inject constructor(val mineRepository: MineRepository) : BaseViewModel() {
    private val _userInfo: MutableLiveData<UserInfoData?> = MutableLiveData()
    val userInfo: LiveData<UserInfoData?> = _userInfo
    fun getUserInfo(fromLogin: Boolean = false) {
        //已经登陆的话，先展示缓存，再请求用户信息
        _userInfo.value = UserManager.getCurUser()
        //如果是登录触发的，就不需要重新请求了，刚请求过
        if (!fromLogin) {
            launch {
                val result = mineRepository.getUserInfo()
                if (result.isSuccess) {
                    _userInfo.value = result.getOrNull()
                    //缓存用户信息
                    UserManager.saveUserInfo(result.getOrNull()!!)
                }
            }
        }

    }

    fun logout() {
        launch {
            val result = mineRepository.logout()
            if (result.isSuccess) {
                LoginHelper.logout()
            }
        }
    }
}