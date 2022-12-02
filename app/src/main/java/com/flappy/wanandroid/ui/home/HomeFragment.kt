package com.flappy.wanandroid.ui.home

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @Author: luweiming
 * @Description:一级页面首页
 * @Date: Created in 17:06 2022/8/30
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {
    private lateinit var pageAdapter: HomePageAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun bindViewModel() {

    }


    override fun initView() {

        pageAdapter = HomePageAdapter(childFragmentManager, lifecycle)
        binding.vpHome.adapter = pageAdapter
        TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.discovery)
                1 -> getString(R.string.qa)
                else -> getString(R.string.square)
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.vpHome.adapter = null
    }

}