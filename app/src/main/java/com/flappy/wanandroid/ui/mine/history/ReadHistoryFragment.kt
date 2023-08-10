package com.flappy.wanandroid.ui.mine.history

import androidx.fragment.app.viewModels
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.CommonListFragmentBinding
import com.flappy.wanandroid.ext.goArticleDetail
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:41 2022/12/7
 */
@AndroidEntryPoint
class ReadHistoryFragment : BaseFragment<CommonListFragmentBinding>() {
    private val adapter: ReadHistoryAdapter by lazy { ReadHistoryAdapter() }
    private val viewModel by viewModels<ReadHistoryVM>()
    override fun initView() {
        bindViewModel()
        binding.recyclerView.adapter = adapter
        adapter.itemClick = { p, data -> goArticleDetail(data.title, data.link) }
    }

    override fun getLayoutId(): Int = R.layout.common_list_fragment
    fun bindViewModel() {
        viewModel.histories.observe(this) {
            adapter.addAll(it)
        }
    }
}