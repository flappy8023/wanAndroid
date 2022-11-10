package com.flappy.wanandroid.ui.mine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.UserInfoData
import com.flappy.wanandroid.data.repository.MineRepository
import com.flappy.wanandroid.util.UserManager

/**
 * @Author: luweiming
 * @Description:我的页面对应ViewModel
 * @Date: Created in 22:19 2022/10/17
 */
class MineVM : BaseViewModel() {
    private val _userInfo: MutableLiveData<UserInfoData> = MutableLiveData()
    val userInfo: LiveData<UserInfoData> = _userInfo
    fun getUserInfo() {
        launch {
            val result = MineRepository.getUserInfo()
            if (result.isSuccess) {
                _userInfo.value = result.getOrNull()
                //缓存用户信息
                UserManager.saveUserInfo(result.getOrNull()!!)
            }
        }
    }
}