package com.flappy.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:48 2022/9/1
 */
object JsonUtil {
    private val gson by lazy { Gson() }

    fun <T> toJsonString(src: Any, typeToken: TypeToken<T>): String {
        return gson.toJson(src, typeToken.type)
    }

    fun toJsonString(src: Any): String {
        return gson.toJson(src)
    }

    fun <T> fromJsonString(jsonString: String, typeToken: TypeToken<T>): T? {
        return try {
            gson.fromJson(jsonString, typeToken.type)
        } catch (e: Exception) {
            null
        }
    }

    fun <T> fromJsonString(jsonString: String, objCls: Class<T>): T? {
        return try {
            gson.fromJson(jsonString, objCls)
        } catch (e: java.lang.Exception) {
            null
        }
    }
}