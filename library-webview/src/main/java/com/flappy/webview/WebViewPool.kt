package com.flappy.webview

import android.content.Context
import android.content.MutableContextWrapper
import android.os.Looper
import android.util.Log

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:47 2022/12/15
 */
class WebViewPool private constructor() : IWebViewPool {
    companion object {
        private const val TAG = "WebViewPool"
        fun get() = Holder.instance
    }

    private object Holder {
        val instance = WebViewPool()
    }

    private val webViewCache: MutableList<IRecyclable> = ArrayList(1)
    override fun init(context: Context) {
        if (webViewCache.isEmpty()) {
            Looper.myQueue().addIdleHandler {
                webViewCache.add(create(MutableContextWrapper(context)))
                false
            }
        }
    }

    private fun create(context: Context): RecyclableWebView {
        return RecyclableWebView(context)
    }

    override fun obtain(context: Context): IRecyclable {
        if (webViewCache.isEmpty()) {
            webViewCache.add(create(MutableContextWrapper(context)))
        }
        val recyclable = webViewCache.removeFirst()
        recyclable.reuse(context)
        return recyclable
    }

    override fun recycle(recyclable: IRecyclable) {
        try {
            recyclable.recycle()
        } catch (e: Exception) {
            Log.e(TAG, "recycle catch error${e.message}")
        } finally {
            if (!webViewCache.contains(recyclable)) {
                webViewCache.add(recyclable)
            }
        }
    }

    override fun destroy() {
        val iterator = webViewCache.iterator()
        while (iterator.hasNext()) {
            val webView = iterator.next()
            webView.destroyMe()
            iterator.remove()
        }
    }
}