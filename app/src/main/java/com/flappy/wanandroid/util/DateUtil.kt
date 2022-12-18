package com.flappy.wanandroid.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 18:03 2022/12/1
 */
object DateUtil {
    fun parseToString(time: Long, format: String = "yyyy-MM-dd"): String {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(Date(time))
    }
}