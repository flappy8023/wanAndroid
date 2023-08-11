package com.flappy.wanandroid.ui.home

import androidx.fragment.app.viewModels
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseToolbarFragment
import com.flappy.wanandroid.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:一级页面首页
 * @Date: Created in 17:06 2022/8/30
 */
@AndroidEntryPoint
class HomeFragment : BaseToolbarFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeVM>()
    private lateinit var pageAdapter: HomePageAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
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