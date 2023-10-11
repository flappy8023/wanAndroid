package com.flappy.wanandroid.ui.web

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseToolbarFragment
import com.flappy.wanandroid.databinding.FragmentWebBinding
import com.flappy.wanandroid.util.web.WebHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebFragment : BaseToolbarFragment<FragmentWebBinding>() {
    private var url: String = ""
    private val args by navArgs<WebFragmentArgs>()
    private lateinit var webHolder: WebHolder
    private val viewModel by viewModels<WebVM>()
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

    override fun onDestroyView() {
        super.onDestroyView()
    }
}