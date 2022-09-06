package com.flappy.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.databinding.HomeItemBannerBinding
import com.flappy.wanandroid.vo.BannerItem
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator

/**
 * @Author: luweiming
 * @Description: 首页轮播模块整体adapter，作为首页recyclerview的一部分
 * @Date: Created in 15:49 2022/9/6
 */
class HomeBannerAdapter() :BaseRecyclerViewAdapter<BannerItem,HomeItemBannerBinding>(){


    override fun getItemCount(): Int {
        //作为首页的一个item展示
        return if(getDataList().isNullOrEmpty()) 0 else 1
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
            setIndicator(CircleIndicator(holder.itemView.context))
        }
    }
}