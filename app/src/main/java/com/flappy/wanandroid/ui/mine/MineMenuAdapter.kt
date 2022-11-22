package com.flappy.wanandroid.ui.mine

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.databinding.ItemMineMenuBinding

/**
 * @Author: luweiming
 * @Description:个人主页下方菜单列表适配器
 * @Date: Created in 13:05 2022/11/22
 */
class MineMenuAdapter : BaseRecyclerViewAdapter<Pair<Int, String>, ItemMineMenuBinding>() {
    override fun getLayoutId() = R.layout.item_mine_menu

    override fun bindView(binding: ItemMineMenuBinding, data: Pair<Int, String>, holder: Holder) {
        binding.ivIcon.setImageResource(data.first)
        binding.tvTitle.text = data.second
    }
}