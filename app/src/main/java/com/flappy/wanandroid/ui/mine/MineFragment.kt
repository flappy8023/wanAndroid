package com.flappy.wanandroid.ui.mine

import android.view.View
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.NavMainDirections
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.data.model.UserInfoData
import com.flappy.wanandroid.databinding.FragmentMineBinding
import com.flappy.wanandroid.util.UserManager
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * @Author: luweiming
 * @Description:一级页面
 * @Date: Created in 21:30 2022/10/17
 */
class MineFragment : BaseFragment<FragmentMineBinding, MineVM>() {
    override fun bindViewModel() {
        viewModel.userInfo.observe(this) {
            showUserInfo(it)
        }
    }

    override fun initView() {
        LiveEventBus.get(UserManager.KEY_LOGIN, Boolean::class.java).observe(this) {
            if (it) {
                viewModel.getUserInfo()
            }
        }
        if (UserManager.isLogin()) {
            binding.groupInfo.visibility = View.VISIBLE
            showUserInfo(UserManager.getCurUser())
        }
        binding.btGoLogin.setOnClickListener {
            findNavController().navigate(
                NavMainDirections.goLogin()
            )
        }
    }

    private fun showUserInfo(userInfo: UserInfoData?) {
        binding.apply {
            if (View.VISIBLE != groupInfo.visibility) {
                groupInfo.visibility = View.VISIBLE
            }
            if (View.GONE != btGoLogin.visibility) {
                btGoLogin.visibility = View.GONE
            }

            tvName.text = userInfo?.userInfo?.nickname
            tvLevel.text = buildString {
                append("Lv.")
                append(userInfo?.coinInfo?.level)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_mine
}