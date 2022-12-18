package com.flappy.wanandroid.ui.mine.collection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.CommonListFragmentBinding
import com.flappy.wanandroid.ext.goArticleDetail
import com.google.android.material.divider.MaterialDividerItemDecoration

/**
 * @Author: luweiming
 * @Description: 我的-我的收藏-网址
 * @Date: Created in 15:28 2022/12/7
 */
class CollectedWebsiteFragment : BaseFragment<CommonListFragmentBinding>() {
    private var adapter: CollectedWebsiteAdapter? = null

    //同级页面共用viewModel
    private val viewModel by viewModels<CollectionVM>(ownerProducer = { requireParentFragment() })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.websites.observe(viewLifecycleOwner) {
            adapter?.clear()
            adapter?.addAll(it)
        }
    }

    override fun initView() {
        adapter = CollectedWebsiteAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        adapter?.itemClick = { _, data -> goArticleDetail(data.name, data.link) }

    }

    override fun getLayoutId(): Int = R.layout.common_list_fragment
}