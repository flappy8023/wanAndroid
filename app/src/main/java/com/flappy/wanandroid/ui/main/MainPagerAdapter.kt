package com.flappy.wanandroid.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flappy.wanandroid.ui.home.HomeFragment

/**
 * @Author: luweiming
 * @Description:首页viewpager adapter
 * @Date: Created in 22:30 2022/8/31
 */
class MainPagerAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            else -> HomeFragment()
        }
    }
}