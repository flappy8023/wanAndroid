package com.flappy.wanandroid.ui.system

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentSystemBinding

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:29 2022/10/17
 */
class SystemFragment : BaseFragment<FragmentSystemBinding, SystemVM>() {

    private val adapter by lazy { SystemAdapter() }

    @SuppressLint("NotifyDataSetChanged")
    override fun bindViewModel() {
        viewModel.trees.observe(this) {
            adapter.addAll(it)
//            adapter.notifyItemRangeInserted(0,it.size)
            adapter.notifyDataSetChanged()
        }
    }

    override fun initView() {
        adapter.itemClickCallback =
            { tree -> SystemArticleListActivity.start(requireContext(), tree.id) }
        binding.rvTree.adapter = adapter
        lifecycleScope.launchWhenCreated {
            viewModel.getSystemTrees()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_system
}