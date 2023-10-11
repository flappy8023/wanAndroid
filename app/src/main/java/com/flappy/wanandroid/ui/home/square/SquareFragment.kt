package com.flappy.wanandroid.ui.home.square

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
 * @Description:首页-广场页面
 * @Date: Created in 22:47 2022/11/2
 */
@AndroidEntryPoint
class SquareFragment : BaseVMFragment<HomeDiscoveryFragmentBinding, HomeVM>() {
    private val adapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }
    override fun observe() {
        lifecycleScope.launch {
            viewModel.squareList.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.rvArticles.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        adapter.itemClick = { _, article -> goArticleDetail(article.title, article.link) }
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
    }

    override fun getLayoutId() = R.layout.home_discovery_fragment
}