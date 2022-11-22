package com.flappy.wanandroid.ui.web

import androidx.navigation.fragment.navArgs
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentWebBinding

class WebFragment : BaseFragment<FragmentWebBinding, WebVM>() {
    private var url: String? = ""
    val args by navArgs<WebFragmentArgs>()
    override fun handleArguments() {
        setTitle(args.title)
        url = args.url
    }

    override fun initView() {
        binding.webview.loadUrl(url)

    }

    override fun getLayoutId() = R.layout.fragment_web
    override fun bindViewModel() {
    }
}