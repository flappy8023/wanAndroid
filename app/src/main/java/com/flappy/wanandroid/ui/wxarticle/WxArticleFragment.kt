package com.flappy.wanandroid.ui.wxarticle

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.paging.pagingwithnetwork.reddit.paging.asMergedLoadStates
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.WxArtilcleListBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.vo.WXOfficialAccount
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * @Author: luweiming
 * @Description:微信公账号文章列表页面
 * @Date: Created in 12:53 2022/9/8
 */
class WxArticleFragment : BaseFragment<WxArtilcleListBinding, WechatArticleVM>() {
    private var wechatId: Long? = null

    private val adapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    companion object {
        fun create(wxAccount: WXOfficialAccount): WxArticleFragment {
            return WxArticleFragment().apply {
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
            ViewModelProvider(this, WxVMFactory(wechatId!!)).get(WechatArticleVM::class.java)
    }

    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.wechatArticles.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.rvArticles.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rvArticles.adapter = adapter
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

    override fun getLayoutId(): Int = R.layout.wx_artilcle_list
}