package com.flappy.wanandroid.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:28 2022/8/25
 */
abstract class BaseRecyclerViewAdapter:RecyclerView.Adapter<BaseRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO()
    }
    class Holder(view: View):RecyclerView.ViewHolder(view){
        init {

        }
    }
}