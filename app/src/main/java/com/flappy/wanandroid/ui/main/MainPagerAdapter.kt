package com.flappy.wanandroid.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flappy.wanandroid.ui.home.HomeFragment
import com.flappy.wanandroid.ui.mine.MineFragment
import com.flappy.wanandroid.ui.system.SystemFragment
import com.flappy.wanandroid.ui.wxarticle.WxFragment

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
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SystemFragment()
            2 -> WxFragment()
            else -> MineFragment()
        }
    }
}