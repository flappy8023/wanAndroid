package com.flappy.wanandroid.ui.mine

import android.view.View
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.data.model.UserInfoData
import com.flappy.wanandroid.databinding.FragmentMineBinding
import com.flappy.wanandroid.util.UserManager
import com.flappy.wanandroid.util.login.LoginHelper
import com.flappy.wanandroid.util.login.LoginIntercept

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
        if (LoginHelper.isLogin()) {
            binding.groupInfo.visibility = View.VISIBLE
            //先取缓存
            showUserInfo(UserManager.getCurUser())
            //然后重新请求最新数据
            viewModel.getUserInfo()
        }
        binding.btGoLogin.setOnClickListener {
            LoginIntercept.get().checkLogin({ LoginHelper.goLogin(this) }) {
                viewModel.getUserInfo()
            }
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