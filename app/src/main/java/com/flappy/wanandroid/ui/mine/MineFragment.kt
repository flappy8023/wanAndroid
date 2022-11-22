package com.flappy.wanandroid.ui.mine

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.app.ActivityCompat
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.data.model.UserInfoData
import com.flappy.wanandroid.databinding.FragmentMineBinding
import com.flappy.wanandroid.util.login.LoginHelper

/**
 * @Author: luweiming
 * @Description:一级页面
 * @Date: Created in 21:30 2022/10/17
 */
class MineFragment : BaseFragment<FragmentMineBinding, MineVM>() {
    override fun bindViewModel() {
        viewModel.userInfo.observe(viewLifecycleOwner) {
            it?.let {
                showUserInfo(it)
            } ?: showNotLogin()
        }

    }


    override fun initView() {
        viewModel.getUserInfo()
        binding.btGoLogin.setOnClickListener {
            LoginHelper.goLogin(this)
        }
        binding.logout.setOnClickListener {
            viewModel.logout()
        }
        initMenuList()
        //监听登录、登出状态
        LoginHelper.observerLogin(
            viewLifecycleOwner,
            { viewModel.getUserInfo(true) },
            { showNotLogin() })
    }

    private fun initMenuList() {
        val data =
            listOf(
                R.drawable.ic_round_collections_bookmark_24 to getString(R.string.my_collection),
                R.drawable.ic_round_settings_24 to getString(R.string.settings)
            )
        val adapter = MineMenuAdapter()
        adapter.addAll(data)
        binding.rvMenus.adapter = adapter
    }

    private fun showUserInfo(userInfo: UserInfoData?) {
        binding.apply {
            if (View.VISIBLE != groupInfo.visibility) {
                groupInfo.visibility = View.VISIBLE
            }
            if (View.GONE != btGoLogin.visibility) {
                btGoLogin.visibility = View.GONE
            }
            ivAvatar.setImageDrawable(
                ColorDrawable(
                    ActivityCompat.getColor(
                        requireContext(),
                        R.color.purple_200
                    )
                )
            )
            ivAvatar.setLetter(userInfo?.userInfo?.nickname)
            tvName.text = userInfo?.userInfo?.nickname
            tvLevel.text = buildString {
                append("Lv.")
                append(userInfo?.coinInfo?.level)
            }
            if (logout.visibility != View.VISIBLE) {
                logout.visibility = View.VISIBLE
            }
        }
    }

    private fun showNotLogin() {
        binding.apply {
            if (View.GONE != groupInfo.visibility) {
                groupInfo.visibility = View.GONE
            }
            if (View.VISIBLE != btGoLogin.visibility) {
                btGoLogin.visibility = View.VISIBLE
            }
            if (View.GONE != logout.visibility) {
                logout.visibility = View.GONE
            }
            ivAvatar.setImageResource(R.drawable.icon_user_default)
            ivAvatar.setLetter(null)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_mine
}