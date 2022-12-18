package com.flappy.wanandroid.ui.system

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.android.example.paging.pagingwithnetwork.reddit.paging.asMergedLoadStates
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.SystemArticleListBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * @Author: luweiming
 * @Description:体系二级目录下文章列表
 * @Date: Created in 22:02 2022/10/17
 */
class SystemArticleListFragment : BaseVMFragment<SystemArticleListBinding, SystemArticleVM>() {
    private val adapter = HomeArticleAdapter()
    private var cid: Long = -1L
    override fun handleArguments() {
        arguments?.let {
            cid = it.getLong("cid", -1)
        }
    }

    override fun initViewModel() {
        viewModel =
            ViewModelProvider(this, SystemArticleVMFactory(cid))[SystemArticleVM::class.java]
    }

    override fun initView() {
        binding.rvArticles.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
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

    override fun getLayoutId(): Int = R.layout.system_article_list


    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            if (cid != -1L) {
                viewModel.systemArticles.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
}