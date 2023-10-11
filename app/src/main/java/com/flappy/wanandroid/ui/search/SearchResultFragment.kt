package com.flappy.wanandroid.ui.search

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.SearchResultFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.ui.paging.asMergedLoadStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 2022/10/10
 */
@AndroidEntryPoint
class SearchResultFragment : BaseVMFragment<SearchResultFragmentBinding, SearchVM>() {

    private val resultAdapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter()
    }

    override fun handleArguments() {
        val keyword = arguments?.getString("keyWord", "")
    }

    override fun observe() {
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
        lifecycleScope.launch {
            resultAdapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launch {
            resultAdapter.loadStateFlow.asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvResult.scrollToPosition(0) }
        }
    }

    override fun getLayoutId(): Int = R.layout.search_result_fragment
}