package com.flappy.wanandroid.ui.wechat

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flappy.wanandroid.data.model.WXOfficialAccount

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:57 2022/9/9
 */
class WechatPageAdapter(fragment: Fragment, val wxAccounts: List<WXOfficialAccount>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return wxAccounts.size
    }

    override fun createFragment(position: Int): Fragment {
        return WechatArticleFragment.create(wxAccounts[position])
    }
}