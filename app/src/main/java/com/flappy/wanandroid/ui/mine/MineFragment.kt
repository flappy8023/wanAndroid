package com.flappy.wanandroid.ui.mine

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.data.model.MineMenu
import com.flappy.wanandroid.data.model.UserInfoData
import com.flappy.wanandroid.databinding.FragmentMineBinding
import com.flappy.wanandroid.util.login.LoginHelper
import com.flappy.wanandroid.util.login.LoginIntercept
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:一级页面
 * @Date: Created in 21:30 2022/10/17
 */
@AndroidEntryPoint
class MineFragment : BaseFragment<FragmentMineBinding>() {
    private val viewModel by viewModels<MineVM>()
    fun bindViewModel() {
        viewModel.userInfo.observe(this) {
            it?.let {
                showUserInfo(it)
            } ?: showNotLogin()
        }

    }


    override fun initView() {
        bindViewModel()
        if (LoginHelper.isLogin())
            viewModel.getUserInfo()
        else showNotLogin()
        binding.btGoLogin.setOnClickListener {
            LoginHelper.goLogin(this)
        }
        binding.logout.setOnClickListener {
            viewModel.logout()
        }
        initMenuList()
        //监听登录、登出状态
        LoginHelper.observerLogin(
            this,
            { viewModel.getUserInfo(true) },
            { showNotLogin() })
    }

    private fun initMenuList() {
        val data =
            listOf(
                MineMenu(
                    R.drawable.ic_round_collections_bookmark_24,
                    getString(R.string.my_collection)
                ),
                MineMenu(R.drawable.ic_round_history_24, getString(R.string.read_history)),
                MineMenu(
                    R.drawable.ic_round_share_24,
                    getString(R.string.mine_share)
                ),
                MineMenu(R.drawable.ic_round_settings_24, getString(R.string.settings))
            )
        val adapter = MineMenuAdapter()
        adapter.addAll(data)
        binding.rvMenus.adapter = adapter
        adapter.itemClick = { position, data ->
            when (position) {
                0 -> LoginIntercept.get().checkLogin({ LoginHelper.goLogin(this) },
                    { findNavController().navigate(MineFragmentDirections.actionGoCollection()) })
                1 -> findNavController().navigate(MineFragmentDirections.actionGoReadHistory())
//                2 -> goArticleDetail("", data.subTitle!!)
                3 -> findNavController().navigate(MineFragmentDirections.actionGlobalSettingsFragment())
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