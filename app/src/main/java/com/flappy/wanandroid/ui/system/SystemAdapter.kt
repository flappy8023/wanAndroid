package com.flappy.wanandroid.ui.system

import android.view.LayoutInflater
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.data.model.TreeItem
import com.flappy.wanandroid.databinding.SystemItemTreeBinding
import com.google.android.material.chip.Chip

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:54 2022/10/18
 */
class SystemAdapter : BaseRecyclerViewAdapter<TreeItem, SystemItemTreeBinding>() {
    var itemClickCallback: ((TreeItem) -> Unit)? = null
    override fun getLayoutId(): Int = R.layout.system_item_tree

    override fun bindView(binding: SystemItemTreeBinding, data: TreeItem, holder: Holder) {
        binding.tvTreeTitle.text = data.name
        data.children.map { it.name }.forEach {
            val chip = LayoutInflater.from(binding.root.context)
                .inflate(R.layout.search_item_rec_chip, binding.layoutSubTitles, false) as Chip
            chip.text = it
            chip.setOnClickListener {
                itemClickCallback?.invoke(data.children[holder.bindingAdapterPosition])
            }
            binding.layoutSubTitles.addView(chip)
        }
    }

}