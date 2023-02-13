package com.flappy.wanandroid.ui.mine.history

import com.flappy.util.DateUtil
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.data.model.ReadHistory
import com.flappy.wanandroid.databinding.MineItemReadHistoryBinding

/**
 * @Author: luweiming
 * @Description: 我的-阅读历史列表适配器
 * @Date: Created in 17:28 2023/2/10
 */
class ReadHistoryAdapter : BaseRecyclerViewAdapter<ReadHistory, MineItemReadHistoryBinding>() {
    override fun getLayoutId() = R.layout.mine_item_read_history
    override fun bindView(binding: MineItemReadHistoryBinding, data: ReadHistory, holder: Holder) {
        binding.tvTitle.text = data.title
        binding.tvUrl.text = DateUtil.parseToString(data.time, "yyyy-MM-dd HH:mm")
    }

    interface IItemClick {
        fun click(data: ReadHistory)
    }
}