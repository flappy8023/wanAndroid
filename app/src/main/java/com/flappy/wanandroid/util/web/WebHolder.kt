package com.flappy.wanandroid.util.web

import android.content.Context
import android.graphics.Bitmap
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.flappy.webview.RecyclableWebView
import com.flappy.webview.WebViewPool
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:21 2023/2/8
 */
class WebHolder(val context: Context, webContainer: FrameLayout) : LifecycleEventObserver {
    private var webView: RecyclableWebView = WebViewPool.get().obtain(context)
    private var onTitleCallback: ((String) -> Unit)? = null

    init {
        webContainer.addView(
            webView,
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        webView.apply {
            webViewClient = MyWebViewClient()
            webChromeClient = MyChromeClient()
        }

    }

    fun loadUrl(url: String): WebHolder {
        webView.loadUrl(url)
        return this
    }

    fun setOnTitleCallback(callback: (String) -> Unit): WebHolder {
        onTitleCallback = callback
        return this
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(webView: WebView?, url: String?, favion: Bitmap?) {
            super.onPageStarted(webView, url, favion)
        }

        override fun onPageFinished(p0: WebView?, p1: String?) {
            super.onPageFinished(p0, p1)
            webView.title?.let { onTitleCallback?.invoke(it) }
        }
    }

    private inner class MyChromeClient : WebChromeClient() {
        override fun onReceivedTitle(p0: WebView?, p1: String?) {
            super.onReceivedTitle(p0, p1)
            p1?.let { onTitleCallback?.invoke(p1) }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            WebViewPool.get().recycle(webView)
        }
    }

}