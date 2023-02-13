package com.flappy.wanandroid.data.api

import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import javax.net.ssl.SSLException

/**
 * @Author: luweiming
 * @Description: 异常转化，将其他异常统一转化为ApiException
 * @Date: Created in 15:02 2023/2/7
 */
object ExceptionHandle {
    fun handleException(throwable: Throwable?): ApiException {
        return throwable?.let {
            when (it) {
                is ApiException -> it
                is HttpException, is ConnectException -> ApiException(ApiError.NETWORK_ERROR)
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> ApiException(
                    ApiError.PARSE_ERROR
                )
                is SSLException -> ApiException(ApiError.SSL_ERROR)
                is SocketTimeoutException, is UnknownHostException -> ApiException(ApiError.TIMEOUT_ERROR)
                else -> ApiException(ApiError.UNKNOWN)

            }
        } ?: ApiException(ApiError.UNKNOWN)
    }
}