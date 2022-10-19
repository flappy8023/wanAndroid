package com.flappy.wanandroid.ui.system

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.databinding.SystemItemTreeBinding
import com.flappy.wanandroid.vo.TreeItem

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
        binding.layoutSubTitles.setLabels(data.children.map { it.name })
        binding.layoutSubTitles.onItemClick =
            { index -> itemClickCallback?.invoke(data.children[index]) }
    }

}