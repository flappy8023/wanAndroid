package com.flappy.wanandroid.data.api

import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @Author: luweiming
 * @Description: 一种用于处理接口请求异常的方法，不需要再在外部捕获异常。暂未启用
 * @Date: Created in 13:18 2022/9/7
 */
class ApiCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        //returnType必须是call<T>类型
        check(getRawType(returnType) == Call::class.java) { "$returnType must be retrofit.call" }
        //returnType必须是参数化类型
        check(returnType is ParameterizedType) { "$returnType must be parameterized" }
        //取出Call<T>中的T，检查是否是ApiResult<T>
        val apiResultType = getParameterUpperBound(0, returnType)
        check(getRawType(apiResultType) == ApiResult::class.java) { "$apiResultType must be ApiResponse" }
        check(apiResultType is ParameterizedType) { "$apiResultType must be parameterized" }
        //去除ApiResult<T> 中的T，即ApiResponse
        val responseType = getParameterUpperBound(0, apiResultType)
        return ApiResultCallAdapter<Any>(responseType)
    }

    class ApiResultCallAdapter<T>(val type: Type) : CallAdapter<T, Call<ApiResult<T>>> {
        override fun responseType(): Type = type

        override fun adapt(call: Call<T>): Call<ApiResult<T>> {
            return ApiResultCall(call)
        }
    }

    class ApiResultCall<T>(val delegate: Call<T>) : Call<ApiResult<T>> {
        override fun clone(): Call<ApiResult<T>> {
            return ApiResultCall(delegate.clone())
        }

        override fun execute(): Response<ApiResult<T>> {
            throw UnsupportedOperationException("ApiResultCall unSupport synchronous execution")
        }

        /**
         * retrofit处理suspend方法会调用此处，回调callback.onResponse后suspend方法成功返回，回调callback.onFailure
         * 后suspend方法会抛出异常，为了不在业务层捕获异常，此处永远回调callback.onResponse。成功时返回ApiResult.Success,
         * 失败时返回ApiResult.Failure
         * @param callback Callback<ApiResult<T>>
         */
        override fun enqueue(callback: Callback<ApiResult<T>>) {
            //delete做实际的网络请求
            delegate.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        val apiResult = if (null == response.body()) {
                            ApiResult.Failure(ApiError.dataIsNull.code, ApiError.dataIsNull.msg)
                        } else {
                            ApiResult.Success(response.body()!!)
                        }
                        callback.onResponse(this@ApiResultCall, Response.success(apiResult))
                    } else {
                        //http status错误
                        val failResult = ApiResult.Failure(
                            ApiError.httpStatusCode.code,
                            ApiError.httpStatusCode.msg
                        )
                        callback.onResponse(this@ApiResultCall, Response.success(failResult))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    val errorResult =
                        ApiResult.Failure(ApiError.unknownError.code, ApiError.unknownError.msg)
                    callback.onResponse(this@ApiResultCall, Response.success(errorResult))
                }
            })
        }

        override fun isExecuted(): Boolean {
            return delegate.isExecuted
        }

        override fun cancel() {
            delegate.cancel()
        }

        override fun isCanceled(): Boolean {
            return delegate.isCanceled
        }

        override fun request(): Request {
            return delegate.request()
        }

        override fun timeout(): Timeout {
            return delegate.timeout()
        }
    }
}