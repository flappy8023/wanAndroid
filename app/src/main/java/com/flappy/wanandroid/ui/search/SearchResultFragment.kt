package com.flappy.wanandroid.ui.search

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.paging.pagingwithnetwork.reddit.paging.asMergedLoadStates
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.SearchResultFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 2022/10/10
 */
class SearchResultFragment : BaseFragment<SearchResultFragmentBinding, SearchVM>() {


    private val resultAdapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter()
    }

    override fun handleArguments() {
        val keyword = arguments?.let {
            it.getString("keyWord", "")
        }
    }

    override fun bindViewModel() {
    }

    fun doSearch(keyWords: String) {
        lifecycleScope.launch {
            resultAdapter.refresh()
            viewModel.searchArticles(keyWords).collectLatest {
                resultAdapter.submitData(it)
            }
        }
    }

    override fun initView() {


        binding.rvResult.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvResult.adapter = resultAdapter
        lifecycleScope.launchWhenCreated {
            resultAdapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            resultAdapter.loadStateFlow.asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvResult.scrollToPosition(0) }
        }
    }

    override fun getLayoutId(): Int = R.layout.search_result_fragment
}