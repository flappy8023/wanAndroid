package com.flappy.wanandroid.ext

import android.content.res.Resources

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:01 2022/11/10
 */
val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()