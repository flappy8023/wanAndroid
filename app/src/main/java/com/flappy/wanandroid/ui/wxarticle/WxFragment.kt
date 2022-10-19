package com.flappy.wanandroid.ui.wxarticle

import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentWechatBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @Author: luweiming
 * @Description:微信公众号主页面
 * @Date: Created in 22:29 2022/9/9
 */
class WxFragment : BaseFragment<FragmentWechatBinding, WechatVM>() {
    override fun bindViewModel() {
        viewModel.wechatAccounts.observe(this){
            val adapter = WechatPageAdapter(this,it)
            binding.vpWechat.adapter = adapter
            TabLayoutMediator(binding.tabWechat, binding.vpWechat) { tab, position -> tab.text = it[position].name }.attach()

        }
    }

    override fun initView() {
        lifecycleScope.launchWhenCreated {
            viewModel.getWechatAccountList()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_wechat
}