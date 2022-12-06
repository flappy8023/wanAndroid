package com.flappy.wanandroid.ui.mine

import android.view.View
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.data.model.MineMenu
import com.flappy.wanandroid.databinding.ItemMineMenuBinding

/**
 * @Author: luweiming
 * @Description:个人主页下方菜单列表适配器
 * @Date: Created in 13:05 2022/11/22
 */
class MineMenuAdapter : BaseRecyclerViewAdapter<MineMenu, ItemMineMenuBinding>() {
    override fun getLayoutId() = R.layout.item_mine_menu

    override fun bindView(binding: ItemMineMenuBinding, data: MineMenu, holder: Holder) {
        binding.ivIcon.setImageResource(data.icon)
        binding.tvTitle.text = data.title
        data.subTitle?.let {
            binding.tvSubTitle.visibility = View.VISIBLE
            binding.tvSubTitle.text = it
        } ?: kotlin.run { binding.tvSubTitle.visibility = View.GONE }
    }
}