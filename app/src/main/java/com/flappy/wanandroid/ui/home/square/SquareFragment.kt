package com.flappy.wanandroid.ui.home.square

import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.HomeContentFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest

/**
 * @Author: luweiming
 * @Description:首页-广场页面
 * @Date: Created in 22:47 2022/11/2
 */
class SquareFragment : BaseFragment<HomeContentFragmentBinding, SquareVM>() {
    private val adapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }
    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.squareList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.rvArticles.adapter = adapter
    }

    override fun getLayoutId() = R.layout.home_content_fragment
}