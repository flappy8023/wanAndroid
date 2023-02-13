package com.flappy.wanandroid.data.api

/**
 * @Author: luweiming
 * @Description:定义请求错误类型，code负数为了与服务器返回code区分
 * @Date: Created in 13:14 2022/9/7
 */
enum class ApiError(private val code: Int, private val err: String) {

    /**
     * 未知错误
     *
     */
    UNKNOWN(1000, "请求失败，请稍后再试"),

    /**
     * 解析错误
     *
     */
    PARSE_ERROR(1001, "解析错误，请稍后再试"),

    /**
     * 网络错误
     *
     */
    NETWORK_ERROR(1002, "网络连接错误，请稍后重试"),

    /**
     * 证书错误
     *
     */
    SSL_ERROR(1003, "证书出错，请稍后再试"),

    /**
     * 连接超时
     *
     */
    TIMEOUT_ERROR(1004, "网络连接超时，请稍后再试");

    fun getCode() = code

    fun getErrorMsg() = err


}
