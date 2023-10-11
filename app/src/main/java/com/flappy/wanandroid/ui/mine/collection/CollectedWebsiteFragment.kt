package com.flappy.wanandroid.ui.mine.collection

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.CommonListFragmentBinding
import com.flappy.wanandroid.util.goArticleDetail
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description: 我的-我的收藏-网址
 * @Date: Created in 15:28 2022/12/7
 */
@AndroidEntryPoint
class CollectedWebsiteFragment : BaseVMFragment<CommonListFragmentBinding, CollectionVM>() {
    private val adapter: CollectedWebsiteAdapter by lazy { CollectedWebsiteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.websites.observe(viewLifecycleOwner) {
            adapter.addAll(it)
        }
    }

    override fun observe() {
    }

    override fun initView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        adapter.itemClick = { _, data -> goArticleDetail(data.name, data.link) }

    }

    override fun getLayoutId(): Int = R.layout.common_list_fragment
}