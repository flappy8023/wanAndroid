package com.flappy.wanandroid.ui.web

import androidx.navigation.fragment.navArgs
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.FragmentWebBinding
import com.flappy.webview.WebViewPool

class WebFragment : BaseVMFragment<FragmentWebBinding, WebVM>() {
    private var url: String? = ""
    private val args by navArgs<WebFragmentArgs>()
    private val iRecyclable by lazy { WebViewPool.get().obtain(requireContext()) }
    private val webView by lazy { iRecyclable.asWebView() }
    override fun handleArguments() {
        setTitle(args.title)
        url = args.url
    }

    override fun initView() {
        binding.container.addView(webView)
        webView.loadUrl(url)
    }

    override fun getLayoutId() = R.layout.fragment_web
    override fun bindViewModel() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        WebViewPool.get().recycle(iRecyclable)
    }
}