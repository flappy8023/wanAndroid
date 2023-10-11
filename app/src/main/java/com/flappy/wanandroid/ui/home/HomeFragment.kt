package com.flappy.wanandroid.ui.home

import android.util.Log
import androidx.fragment.app.Fragment
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseToolbarFragment
import com.flappy.wanandroid.databinding.FragmentHomeBinding
import com.flappy.wanandroid.ui.home.discovery.DiscoveryFragment
import com.flappy.wanandroid.ui.home.qa.QAFragment
import com.flappy.wanandroid.ui.home.square.SquareFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:一级页面首页
 * @Date: Created in 17:06 2022/8/30
 */
@AndroidEntryPoint
class HomeFragment : BaseToolbarFragment<FragmentHomeBinding>() {
    companion object {
        private const val TAG = "HomeFragment"
    }

    private val fragments = listOf<Fragment>(DiscoveryFragment(), QAFragment(), SquareFragment())
    private lateinit var pageAdapter: HomePageAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun initView() {
        Log.d(
            TAG,
            "initView: viewpage's adapter is ${binding.vpHome.adapter?.let { "not null" } ?: "null"}"
        )
        if (binding.vpHome.adapter == null) {
            pageAdapter =
                HomePageAdapter(fragments, childFragmentManager, viewLifecycleOwner.lifecycle)
            binding.vpHome.offscreenPageLimit = fragments.size - 1
            binding.vpHome.adapter = pageAdapter
        }
        TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.discovery)
                1 -> getString(R.string.qa)
                else -> getString(R.string.square)
            }
        }.attach()
    }

}