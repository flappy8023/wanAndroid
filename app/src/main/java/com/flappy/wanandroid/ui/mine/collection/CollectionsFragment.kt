package com.flappy.wanandroid.ui.mine.collection

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.CommonViewpagerWithTabFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:30 2022/12/7
 */
@AndroidEntryPoint
class CollectionsFragment : BaseFragment<CommonViewpagerWithTabFragmentBinding>() {
    override fun initView() {
        binding.vpContent.adapter = CollectionPagerAdapter(this)
        TabLayoutMediator(
            binding.tablayout, binding.vpContent
        ) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.collected_article)
                else -> getString(R.string.collected_website)
            }
        }.attach()

    }

    override fun getLayoutId(): Int = R.layout.common_viewpager_with_tab_fragment
}