package com.flappy.wanandroid.ui.mine.collection

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.data.model.WebSiteBean
import com.flappy.wanandroid.databinding.MineCollectedWebsiteItemBinding

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:51 2022/12/8
 */
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