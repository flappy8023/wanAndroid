package com.flappy.webview

import android.content.Context

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:24 2022/12/15
 */
interface IRecyclable {
    fun recycle()

    fun reuse(context: Context)

    fun destroyMe()
}