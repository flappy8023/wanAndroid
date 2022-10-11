package com.flappy.wanandroid.ui.search

import androidx.lifecycle.Observer
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.SearchRecFragmentBinding

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:09 2022/9/21
 */
class SearchRecFragment:BaseFragment<SearchRecFragmentBinding,SearchVM>() {
    override fun bindViewModel() {
        viewModel.hotWords.observe(this, Observer {
            binding.labelLayoutHot.setLabels(it)
        })
    }

    override fun initView() {
        viewModel.requestHotWords()
    }

    override fun getLayoutId(): Int  = R.layout.search_rec_fragment
}