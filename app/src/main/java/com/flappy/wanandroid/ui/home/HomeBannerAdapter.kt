package com.flappy.wanandroid.ui.home

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.databinding.HomeItemBannerBinding
import com.flappy.wanandroid.vo.BannerItem
import com.youth.banner.indicator.CircleIndicator

/**
 * @Author: luweiming
 * @Description: 首页轮播模块整体adapter，作为首页recyclerview的一部分
 * @Date: Created in 15:49 2022/9/6
 */
class HomeBannerAdapter() :BaseRecyclerViewAdapter<BannerItem,HomeItemBannerBinding>(){

    var itemClick: ((Int, BannerItem) -> Unit)? = null
    override fun getItemCount(): Int {
        //作为首页的一个item展示
        return if (getDataList().isEmpty()) 0 else 1
    }

    override fun getLayoutId(): Int {
        return R.layout.home_item_banner
    }

    override fun bindView(
        binding: HomeItemBannerBinding,
        data: BannerItem,
        holder: BaseRecyclerViewAdapter<BannerItem, HomeItemBannerBinding>.Holder
    ) {
        binding.banner.apply {
            setAdapter(ImageBannerAdapter(getDataList()))
            indicator = CircleIndicator(holder.itemView.context)
            setOnBannerListener { data, position ->
                itemClick?.invoke(
                    position,
                    data as BannerItem
                )
            }
        }
    }
}