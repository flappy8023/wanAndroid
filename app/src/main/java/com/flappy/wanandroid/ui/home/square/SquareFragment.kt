package com.flappy.wanandroid.ui.home.square

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.HomeDiscoveryFragmentBinding
import com.flappy.wanandroid.ext.goArticleDetail
import com.flappy.wanandroid.paging.asMergedLoadStates
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.ui.home.HomeVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:首页-广场页面
 * @Date: Created in 22:47 2022/11/2
 */
@AndroidEntryPoint
class SquareFragment : BaseFragment<HomeDiscoveryFragmentBinding>() {
    private val viewModel by viewModels<HomeVM>()
    private val adapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }
    fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.squareList.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        bindViewModel()
        binding.rvArticles.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        adapter.itemClick = { _, article -> goArticleDetail(article.title, article.link) }
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvArticles.scrollToPosition(0) }
        }
    }

    override fun getLayoutId() = R.layout.home_discovery_fragment
}