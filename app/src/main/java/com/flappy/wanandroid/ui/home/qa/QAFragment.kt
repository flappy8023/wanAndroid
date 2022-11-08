package com.flappy.wanandroid.ui.home.qa

import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.HomeDiscoveryFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest

/**
 * @Author: luweiming
 * @Description:首页-问答子页面
 * @Date: Created in 22:41 2022/11/2
 */
class QAFragment : BaseFragment<HomeDiscoveryFragmentBinding, HomeQAVM>() {
    private val articleAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getQAList().collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.rvArticles.adapter = articleAdapter
    }

    override fun getLayoutId() = R.layout.home_discovery_fragment
}