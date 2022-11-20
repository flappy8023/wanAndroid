package com.flappy.wanandroid.ui.home.qa

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.android.example.paging.pagingwithnetwork.reddit.paging.asMergedLoadStates
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.HomeDiscoveryFragmentBinding
import com.flappy.wanandroid.ext.goArticleDetail
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * @Author: luweiming
 * @Description:首页-问答子页面
 * @Date: Created in 22:41 2022/11/2
 */
class QAFragment : BaseFragment<HomeDiscoveryFragmentBinding, HomeQAVM>() {
    private val articleAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
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
        lifecycleScope.launchWhenCreated {
            articleAdapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            articleAdapter.loadStateFlow.asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvArticles.scrollToPosition(0) }
        }
    }

    override fun getLayoutId() = R.layout.home_discovery_fragment
}