package com.flappy.wanandroid.ui.wechat

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseToolbarFragment
import com.flappy.wanandroid.databinding.FragmentWechatBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:微信公众号主页面
 * @Date: Created in 22:29 2022/9/9
 */
@AndroidEntryPoint
class WechatFragment : BaseToolbarFragment<FragmentWechatBinding>() {
    val viewModel by viewModels<WechatVM>()
    fun bindViewModel() {
        viewModel.wechatAccounts.observe(viewLifecycleOwner) {
            val adapter = WechatPageAdapter(this, it)
            binding.vpWechat.adapter = adapter
            TabLayoutMediator(binding.tabWechat, binding.vpWechat) { tab, position ->
                tab.text = it[position].name
            }.attach()

        }
    }

    override fun initView() {
        bindViewModel()
        lifecycleScope.launchWhenCreated {
            viewModel.getWechatAccountList()
        }
        bindViewModel()
    }

    override fun getLayoutId(): Int = R.layout.fragment_wechat
}