package com.flappy.wanandroid.ui.system

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseToolbarFragment
import com.flappy.wanandroid.databinding.FragmentSystemBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:一级页面 体系
 * @Date: Created in 21:29 2022/10/17
 */
@AndroidEntryPoint
class SystemFragment : BaseToolbarFragment<FragmentSystemBinding>() {

    private val adapter by lazy { SystemAdapter() }

    private val viewModel by viewModels<SystemVM>()

    @SuppressLint("NotifyDataSetChanged")
    private fun observe() {
        viewModel.trees.observe(viewLifecycleOwner) {
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun initView() {
        adapter.itemClickCallback =
            { tree -> findNavController().navigate(SystemFragmentDirections.toSystemList(tree.id)) }
        binding.rvTree.adapter = adapter
        observe()
    }

    override fun getLayoutId(): Int = R.layout.fragment_system
}