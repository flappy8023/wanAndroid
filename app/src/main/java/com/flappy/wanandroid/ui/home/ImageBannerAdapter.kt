package com.flappy.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flappy.wanandroid.R
import com.flappy.wanandroid.vo.BannerItem
import com.youth.banner.adapter.BannerAdapter

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:35 2022/9/6
 */
class ImageBannerAdapter(bannerItems:List<BannerItem>):BannerAdapter<BannerItem,ImageBannerAdapter.BannerHolder>(bannerItems) {

    inner class BannerHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvName = itemView.findViewById<TextView>(R.id.tv_title)
        val ivBanner = itemView.findViewById<ImageView>(R.id.iv_banner)
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerHolder {
        return BannerHolder(LayoutInflater.from(parent?.context).inflate(R.layout.home_item_banner_item,parent,false))
    }

    override fun onBindView(holder: BannerHolder?, data: BannerItem?, position: Int, size: Int) {
        Glide.with(holder?.itemView!!).load(data?.imagePath).into(holder.ivBanner)
        holder.tvName.text = data?.title
    }
}