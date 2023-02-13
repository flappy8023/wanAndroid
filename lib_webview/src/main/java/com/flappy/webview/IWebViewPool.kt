package com.flappy.webview

import android.content.Context

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:42 2022/12/15
 */
interface IWebViewPool {
    fun init(context: Context)

    fun obtain(context: Context): RecyclableWebView

    fun recycle(recyclable: IRecyclable)

    fun destroy()
}