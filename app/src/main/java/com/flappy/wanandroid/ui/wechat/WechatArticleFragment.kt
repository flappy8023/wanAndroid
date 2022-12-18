package com.flappy.wanandroid.ui.wechat

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.paging.pagingwithnetwork.reddit.paging.asMergedLoadStates
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.data.model.WXOfficialAccount
import com.flappy.wanandroid.databinding.WechatArtilcleListBinding
import com.flappy.wanandroid.ext.goArticleDetail
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * @Author: luweiming
 * @Description:微信公账号文章列表页面
 * @Date: Created in 12:53 2022/9/8
 */
class WechatArticleFragment : BaseVMFragment<WechatArtilcleListBinding, WechatArticleVM>() {
    private var wechatId: Long? = null

    private val adapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    companion object {
        fun create(wxAccount: WXOfficialAccount): WechatArticleFragment {
            return WechatArticleFragment().apply {
                arguments = Bundle().apply {
                    putLong("id", wxAccount.id)
                }
            }
        }
    }

    override fun handleArguments() {
        arguments?.let {
            wechatId = it.getLong("id", 0L)
        }
    }

    override fun initViewModel() {
        viewModel =
            ViewModelProvider(this, WechatArticleVMFactory(wechatId!!))[WechatArticleVM::class.java]
    }

    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.wechatArticles.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.rvArticles.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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
        adapter.itemClick = { _, article -> goArticleDetail(article.title, article.link) }

    }

    override fun getLayoutId(): Int = R.layout.wechat_artilcle_list
}