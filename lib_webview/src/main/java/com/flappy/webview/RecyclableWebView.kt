package com.flappy.webview

import android.content.Context
import android.content.MutableContextWrapper
import android.view.ViewGroup
import com.tencent.smtt.export.external.interfaces.IX5WebSettings
import com.tencent.smtt.sdk.CookieManager
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:25 2022/12/15
 */
class RecyclableWebView(context: Context) : IRecyclable, WebView(context) {

    init {
        settings.apply {
            allowFileAccess = true
            javaScriptEnabled = true
            //内容适应屏幕
            useWideViewPort = true
            loadWithOverviewMode = true
            setSupportZoom(true)
            mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            setGeolocationEnabled(true)
            setAppCacheEnabled(true)
            cacheMode = WebSettings.LOAD_DEFAULT
            domStorageEnabled = true
            displayZoomControls = false
        }
        settingsExtension?.apply {
            setContentCacheEnable(true)
            setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY)
        }
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
    }

    override fun recycle() {
        stopLoading()
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        clearHistory()
        pauseTimers()
        webChromeClient = null
        webViewClient = null
        val parent = parent
        if (parent != null) {
            (parent as ViewGroup).removeView(this)
        }
        val contextWrapper = context as MutableContextWrapper
        contextWrapper.baseContext = context.applicationContext
    }

    override fun reuse(context: Context) {
        val contextWrapper = getContext() as MutableContextWrapper
        contextWrapper.baseContext = context
        clearHistory()
        resumeTimers()
    }

    override fun destroyMe() {
        removeAllViews()
        destroy()
    }
}