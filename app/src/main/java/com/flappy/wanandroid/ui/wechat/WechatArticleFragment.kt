package com.flappy.wanandroid.ui.wechat

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.data.model.WXOfficialAccount
import com.flappy.wanandroid.databinding.WechatArtilcleListBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import com.flappy.wanandroid.util.goArticleDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:微信公账号文章列表页面
 * @Date: Created in 12:53 2022/9/8
 */
@AndroidEntryPoint
class WechatArticleFragment : BaseVMFragment<WechatArtilcleListBinding, WechatVM>() {
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


    override fun observe() {
        lifecycleScope.launch {
            viewModel.wechatArticles(wechatId!!).collectLatest {
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
        adapter.itemClick = { _, article -> goArticleDetail(article.title, article.link) }
    }

    override fun getLayoutId(): Int = R.layout.wechat_artilcle_list
}