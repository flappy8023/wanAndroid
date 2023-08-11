package com.flappy.wanandroid.ui.home.discovery

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.HomeDiscoveryFragmentBinding
import com.flappy.wanandroid.ext.goArticleDetail
import com.flappy.wanandroid.paging.asMergedLoadStates
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.ui.home.HomeBannerAdapter
import com.flappy.wanandroid.ui.home.HomeTopAdapter
import com.flappy.wanandroid.ui.home.HomeVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * @Author: luweiming
 * @Description:首页-第一个子页面
 * @Date: Created in 22:47 2022/11/2
 */
@AndroidEntryPoint
class DiscoveryFragment : BaseFragment<HomeDiscoveryFragmentBinding>() {
    private val viewModel by viewModels<HomeVM>()
    private val articleAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }
    private val bannerAdapter: HomeBannerAdapter by lazy { HomeBannerAdapter() }
    private val topAdapter: HomeTopAdapter by lazy { HomeTopAdapter() }
    fun bindViewModel() {
        viewModel.banners.observe(viewLifecycleOwner) {
            bannerAdapter.clear()
            bannerAdapter.addAll(it)
        }
        viewModel.tops.observe(viewLifecycleOwner) {
            topAdapter.clear()
            topAdapter.addAll(it)
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
        bindViewModel()
        initListener()
        binding.apply {
            rvArticles.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            //为了解决分页请求一次性加载的问题，将首页所有的内容放到一个recyclerview进行展示，使用ConcatAdapter聚合轮播、置顶、文章列表的适配器
            val adapter = ConcatAdapter(bannerAdapter, topAdapter, articleAdapter)
            rvArticles.adapter = adapter
            //fragment重建后恢复recyclerview滚动位置
//            bannerAdapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
//            topAdapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
//            articleAdapter.setStateRestorationPolicy(StateRestorationPolicy.PREVENT_WHEN_EMPTY)

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

    override fun getLayoutId() = R.layout.home_discovery_fragment

    private fun initListener() {
        articleAdapter.itemClick =
            { _, article ->
                goArticleDetail(article.title, article.link)
            }
        bannerAdapter.itemClick = { _, banner -> goArticleDetail(banner.title, banner.url) }
        topAdapter.itemClick = { _, article -> goArticleDetail(article.title, article.link) }
    }
}