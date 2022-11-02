package com.flappy.wanandroid.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flappy.wanandroid.ui.home.content.HomeContentFragment
import com.flappy.wanandroid.ui.home.qa.QAFragment
import com.flappy.wanandroid.ui.home.square.SquareFragment

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:00 2022/11/2
 */
class HomePageAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeContentFragment()
            1 -> QAFragment()
            else -> SquareFragment()
        }
    }


}