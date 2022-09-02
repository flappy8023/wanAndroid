package com.flappy.wanandroid.ui.home

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.paging.pagingwithnetwork.reddit.paging.asMergedLoadStates
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:06 2022/8/30
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {
    private val adapter: ArticleAdapter by lazy { ArticleAdapter() }
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.articles.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.apply {
            rvArticles.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvArticles.adapter = adapter
            swipeRefresh.setOnRefreshListener { adapter.refresh() }
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
}