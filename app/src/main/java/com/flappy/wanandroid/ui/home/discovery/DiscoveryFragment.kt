package com.flappy.wanandroid.ui.home.discovery

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.HomeDiscoveryFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.ui.home.HomeBannerAdapter
import com.flappy.wanandroid.ui.home.HomeTopAdapter
import com.flappy.wanandroid.ui.home.HomeVM
import com.flappy.wanandroid.util.goArticleDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:首页-第一个子页面
 * @Date: Created in 22:47 2022/11/2
 */
@AndroidEntryPoint
class DiscoveryFragment : BaseVMFragment<HomeDiscoveryFragmentBinding, HomeVM>() {
    private lateinit var articleAdapter: HomeArticleAdapter
    private lateinit var bannerAdapter: HomeBannerAdapter
    private lateinit var topAdapter: HomeTopAdapter
    private lateinit var concatAdapter: ConcatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //页面创建后，请求轮播数据、置顶内容、文章列表
        viewModel.getBanners()
        viewModel.getTops()
    }

    override fun observe() {
        viewModel.banners.observe(viewLifecycleOwner) {
            bannerAdapter.addAll(it)
        }
        viewModel.tops.observe(viewLifecycleOwner) {
            topAdapter.addAll(it)
        }
        lifecycleScope.launch {

            viewModel.articles.collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

    override fun initView() {
        topAdapter = HomeTopAdapter()
        bannerAdapter = HomeBannerAdapter()
        articleAdapter = HomeArticleAdapter()
        concatAdapter = ConcatAdapter(bannerAdapter, topAdapter, articleAdapter)
        initListener()
        binding.apply {
            rvArticles.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            //为了解决分页请求一次性加载的问题，将首页所有的内容放到一个recyclerview进行展示，使用ConcatAdapter聚合轮播、置顶、文章列表的适配器
            rvArticles.adapter = concatAdapter

            swipeRefresh.setOnRefreshListener {
                viewModel.getBanners()
                viewModel.getTops()
                articleAdapter.refresh()
            }
        }
        lifecycleScope.launch {
            articleAdapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }

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