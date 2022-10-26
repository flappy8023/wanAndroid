package com.flappy.wanandroid.ui.web

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.config.KeyConstant
import com.flappy.wanandroid.databinding.FragmentWebBinding

class WebFragment : BaseFragment<FragmentWebBinding, WebVM>() {
    private var title: String? = ""
    private var url: String? = ""
    override fun handleArguments() {
        arguments?.let {
            title = it.getString(KeyConstant.KEY_TITLE)
            url = it.getString(KeyConstant.KEY_URL)
        }
    }

    override fun initView() {
        binding.webview.loadUrl(url)

    }

    override fun getLayoutId() = R.layout.fragment_web
    override fun bindViewModel() {
    }
}