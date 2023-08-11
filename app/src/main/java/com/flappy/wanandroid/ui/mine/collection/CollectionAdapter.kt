package com.flappy.wanandroid.ui.mine.collection

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.data.model.WebSiteBean
import com.flappy.wanandroid.databinding.MineCollectedWebsiteItemBinding

/**
 * @Author flappy8023
 * @Description //TODO
 * @Date 2023年08月11日 10:29
 **/
class CollectedWebsiteAdapter :
    BaseRecyclerViewAdapter<WebSiteBean, MineCollectedWebsiteItemBinding>() {
    override fun getLayoutId(): Int = R.layout.mine_collected_website_item

    override fun bindView(
        binding: MineCollectedWebsiteItemBinding,
        data: WebSiteBean,
        holder: Holder
    ) {
        binding.tvTitle.text = data.name
        binding.tvUrl.text = data.link
    }
}


class CollectionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> CollectedArticleFragment()
        else -> CollectedWebsiteFragment()
    }
}