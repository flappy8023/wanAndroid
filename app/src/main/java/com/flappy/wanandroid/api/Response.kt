package com.flappy.wanandroid.api

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:39 2022/8/22
 */
class Response<T>(var errorCode: Int, var errorMsg: String, var data: T?) {
    /**
     * 简化接口是否成功的判断
     */
    val isSuccess = errorCode == 0

    /**
     * 未登录
     */
    val notLoggedIn = errorCode == -1001
}