package com.flappy.wanandroid.ui.system

import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.SystemArticleListBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest

/**
 * @Author: luweiming
 * @Description:体系二级目录下文章列表
 * @Date: Created in 22:02 2022/10/17
 */
class SystemArticleListFragment : BaseFragment<SystemArticleListBinding, SystemVM>() {
    private val adapter = HomeArticleAdapter()
    private var cid: Long = -1L
    override fun handleArguments() {
        arguments?.let {
            cid = it.getLong("cid", -1)
        }
    }

    override fun initView() {
        binding.rvArticles.adapter = adapter
    }

    override fun getLayoutId(): Int = R.layout.system_article_list


    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            if (cid != -1L) {
                viewModel.getArticleByTreeId(cid).collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
}