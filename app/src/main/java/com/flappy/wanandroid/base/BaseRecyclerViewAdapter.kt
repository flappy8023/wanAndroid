package com.flappy.wanandroid.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: luweiming
 * @Description:recyclerview适配器基类
 * @Date: Created in 21:28 2022/8/25
 */
abstract class BaseRecyclerViewAdapter<T, VB : ViewDataBinding> :
    RecyclerView.Adapter<BaseRecyclerViewAdapter<T, VB>.Holder>() {
    companion object {
        const val CLICK_TYPE_ITEM = -1
    }

    private val list: MutableList<T> by lazy { mutableListOf() }

    /**
     * 点击回调,type用于区分点击区域
     */
    private var onItemClick: ((position: Int, type: Int) -> Unit)? = null
    abstract fun getLayoutId(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DataBindingUtil.inflate<VB>(
                LayoutInflater.from(parent.context),
                getLayoutId(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        bindView(holder.viewBinding, list[position], holder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(val viewBinding: VB) : RecyclerView.ViewHolder(viewBinding.root) {
        init {
//            为itemView设置通用点击监听
            onItemClick?.let { onClick ->
                viewBinding.root.setOnClickListener {
                    onClick.invoke(
                        layoutPosition,
                        CLICK_TYPE_ITEM
                    )
                }
            }
        }
    }

    abstract fun bindView(binding: VB, data: T, holder: Holder)

    fun addAll(data: List<T>) {
        if (data.isNullOrEmpty()) return
        val sPos = itemCount
        list.addAll(data)
        notifyItemRangeInserted(sPos, itemCount)
    }

    fun add(data: T) {
        if (null == data) return
        list.add(data)
        notifyItemInserted(itemCount)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear(){
        list.clear()
        notifyDataSetChanged()
    }

    fun getDataList():List<T> = list
}