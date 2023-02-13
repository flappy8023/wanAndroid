package com.flappy.wanandroid.data.api

/**
 * @Author: luweiming
 * @Description: 接口异常定义
 * @Date: Created in 16:37 2022/8/22
 */
class ApiException : Exception {
    val errCode: Int
    val errMsg: String
    val throwable: Throwable?

    constructor(code: Int, msg: String = "请求失败，请稍后再试", throwable: Throwable? = null) {
        errCode = code
        errMsg = msg
        this.throwable = throwable
    }

    constructor(error: ApiError, throwable: Throwable? = null) {
        errCode = error.getCode()
        errMsg = error.getErrorMsg()
        this.throwable = throwable
    }
}