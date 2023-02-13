package com.flappy.wanandroid.ui.web

import androidx.navigation.fragment.navArgs
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.FragmentWebBinding
import com.flappy.wanandroid.util.web.WebHolder

class WebFragment : BaseVMFragment<FragmentWebBinding, WebVM>() {
    private var url: String = ""
    private val args by navArgs<WebFragmentArgs>()
    private lateinit var webHolder: WebHolder
    override fun handleArguments() {
        setTitle(args.title)
        url = args.url ?: ""
    }

    override fun initView() {
        webHolder = WebHolder(requireContext(), binding.container).setOnTitleCallback {
            viewModel.addReadHistory(url, args.title)
        }
            .loadUrl(url)
        lifecycle.addObserver(webHolder)
    }

    override fun getLayoutId() = R.layout.fragment_web
    override fun bindViewModel() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}