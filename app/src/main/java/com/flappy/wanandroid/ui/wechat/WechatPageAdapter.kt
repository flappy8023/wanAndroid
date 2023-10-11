package com.flappy.wanandroid.ui.wechat

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flappy.wanandroid.data.model.WXOfficialAccount

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:57 2022/9/9
 */
class WechatPageAdapter(
    fragment: Fragment,
    lifecycle: Lifecycle,
    val wxAccounts: MutableList<WXOfficialAccount>
) :
    FragmentStateAdapter(fragment.childFragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return wxAccounts.size
    }

    override fun getItemId(position: Int): Long {
        return wxAccounts[position].id
    }

    override fun createFragment(position: Int): Fragment {
        return WechatArticleFragment.create(wxAccounts[position])
    }

    override fun containsItem(itemId: Long): Boolean {
        return wxAccounts.any { it.id == itemId }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceData(list: List<WXOfficialAccount>) {
        wxAccounts.clear()
        wxAccounts.addAll(list)
        notifyDataSetChanged()
    }

}