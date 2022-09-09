package com.flappy.wanandroid.api

/**
 * @Author: luweiming
 * @Description:定义请求错误类型，code负数为了与服务器返回code区分
 * @Date: Created in 13:14 2022/9/7
 */
object ApiError {
    /**
     * 响应code成功，但是data为null
     */
    val dataIsNull = Error(-1,"response data is null")

    /**
     * http状态码不是成功
     */
    val httpStatusCode = Error(-2,"server error")

    val unknownError = Error(-3,"unknown error")

}

data class Error(val code: Int, val msg: String)