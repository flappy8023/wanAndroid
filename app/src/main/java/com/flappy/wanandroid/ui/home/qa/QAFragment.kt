package com.flappy.wanandroid.ui.home.qa

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.HomeDiscoveryFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.ui.home.HomeVM
import com.flappy.wanandroid.util.goArticleDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:首页-问答子页面
 * @Date: Created in 22:41 2022/11/2
 */
@AndroidEntryPoint
class QAFragment : BaseVMFragment<HomeDiscoveryFragmentBinding, HomeVM>() {
    private var articleAdapter: HomeArticleAdapter = HomeArticleAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun observe() {

        lifecycleScope.launch {
            viewModel.qaList.collectLatest {
                articleAdapter.submitData(it)
            }

        }
    }

    override fun initView() {
        binding.rvArticles.adapter = articleAdapter
        articleAdapter.itemClick = { _, article -> goArticleDetail(article.title, article.link) }
        binding.swipeRefresh.setOnRefreshListener {
            articleAdapter.refresh()
        }
        lifecycleScope.launch {
            articleAdapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }

    }


    override fun getLayoutId() = R.layout.home_discovery_fragment
}