package com.flappy.wanandroid.ui.home

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
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
    private val articleAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }
    private val bannerAdapter: HomeBannerAdapter by lazy { HomeBannerAdapter() }
    private val topAdapter: HomeTopAdapter by lazy { HomeTopAdapter() }
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun bindViewModel() {
        viewModel.banners.observe(this) {
            bannerAdapter.clear()
            bannerAdapter.addAll(it)
        }
        viewModel.tops.observe(this) {
            topAdapter.clear()
            topAdapter.clear()
        }
        //页面创建后，请求轮播数据、置顶内容、文章列表
        lifecycleScope.launchWhenCreated {
            viewModel.getBanners()
            viewModel.getTops()
            viewModel.articles.collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.apply {
            rvArticles.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            //为了解决分页请求一次性加载的问题，将首页所有的内容放到一个recyclerview进行展示，使用ConcatAdapter聚合轮播、置顶、文章列表的适配器
            rvArticles.adapter = ConcatAdapter(bannerAdapter, topAdapter, articleAdapter)
            swipeRefresh.setOnRefreshListener {
                viewModel.getBanners()
                viewModel.getTops()
                articleAdapter.refresh()
            }
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
}