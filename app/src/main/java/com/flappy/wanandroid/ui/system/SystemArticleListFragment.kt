package com.flappy.wanandroid.ui.system

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.SystemArticleListBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.ui.paging.asMergedLoadStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:体系二级目录下文章列表
 * @Date: Created in 22:02 2022/10/17
 */
@AndroidEntryPoint
class SystemArticleListFragment : BaseVMFragment<SystemArticleListBinding, SystemVM>() {
    private val adapter = HomeArticleAdapter()
    private var cid: Long = -1L
    override fun handleArguments() {
        arguments?.let {
            cid = it.getLong("cid", -1)
        }
    }


    override fun initView() {
        binding.rvArticles.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvArticles.scrollToPosition(0) }
        }
    }

    override fun getLayoutId(): Int = R.layout.system_article_list


    override fun observe() {
        lifecycleScope.launch {
            if (cid != -1L) {
                viewModel.systemArticles(cid).collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
}