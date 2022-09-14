package com.flappy.wanandroid.ui.web

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseActivity
import com.flappy.wanandroid.config.KeyConstant
import com.flappy.wanandroid.databinding.ActivityWebBinding

class WebActivity : BaseActivity<ActivityWebBinding, WebVM>() {
    private var title: String? = ""
    private var url: String? = ""
    override fun handleArguments() {
        intent?.let {
            title = it.getStringExtra(KeyConstant.KEY_TITLE)
            url = it.getStringExtra(KeyConstant.KEY_URL)
        }
    }
    override fun observe() {
    }

    override fun initView() {
        binding.webview.loadUrl(url)

    }

    override fun getLayoutId() = R.layout.activity_web
}