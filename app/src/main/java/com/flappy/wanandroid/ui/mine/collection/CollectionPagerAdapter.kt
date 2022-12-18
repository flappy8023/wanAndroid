package com.flappy.wanandroid.ui.mine.collection

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:25 2022/12/7
 */
class CollectionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> CollectedArticleFragment()
        else -> CollectedWebsiteFragment()
    }
}