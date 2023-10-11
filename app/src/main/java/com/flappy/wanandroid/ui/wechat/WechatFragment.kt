package com.flappy.wanandroid.ui.wechat

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseToolbarFragment
import com.flappy.wanandroid.databinding.FragmentWechatBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:微信公众号主页面
 * @Date: Created in 22:29 2022/9/9
 */
@AndroidEntryPoint
class WechatFragment : BaseToolbarFragment<FragmentWechatBinding>() {
    private val viewModel by viewModels<WechatVM>()
    private lateinit var adapter: WechatPageAdapter


    private fun bindViewModel() {
        viewModel.wechatAccounts.observe(viewLifecycleOwner) {
            adapter.replaceData(it)
        }
    }

    override fun initView() {
        lifecycleScope.launch {
            viewModel.getWechatAccountList()
        }
        adapter = WechatPageAdapter(this, viewLifecycleOwner.lifecycle, mutableListOf())
        binding.vpWechat.adapter = adapter
        TabLayoutMediator(binding.tabWechat, binding.vpWechat) { tab, position ->
            tab.text = adapter.wxAccounts[position].name
        }.attach()
        bindViewModel()
    }

    override fun getLayoutId(): Int = R.layout.fragment_wechat
}