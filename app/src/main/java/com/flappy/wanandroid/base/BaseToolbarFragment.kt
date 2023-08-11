package com.flappy.wanandroid.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.flappy.wanandroid.NavMainDirections
import com.flappy.wanandroid.R
import com.flappy.wanandroid.data.model.UserInfo
import com.flappy.wanandroid.util.UserManager
import com.flappy.wanandroid.util.login.LoginHelper
import com.flappy.widget.AppBarView

/**
 * @Author flappy8023
 * @Description 带有工具栏的Fragment基类
 * @Date 2023年08月11日 12:22
 **/
abstract class BaseToolbarFragment<VB : ViewDataBinding> : BaseFragment<VB>() {
    private var toolbar: Toolbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        toolbar = binding.root.findViewById(R.id.toolbar)
        val navController = findNavController()
        //主页四个页签不需要展示返回箭头
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.systemFragment,
                R.id.wechatFragment,
                R.id.todoFragment
            ), fallbackOnNavigateUpListener = { findNavController().navigateUp() })
        toolbar?.setupWithNavController(navController, appBarConfiguration)
        toolbar?.let {
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
            if (it is AppBarView) {

                it.setTitle(getString(R.string.app_name))
                //展示用户头像
                if (null != UserManager.getCurUser()?.userInfo) {
                    showUserAvatar(UserManager.getCurUser()?.userInfo!!)
                }
                it.setRightClickListener {
                    findNavController().navigate(NavMainDirections.actionGlobalMine())
                }
                it.setLeftClickListener {
                    findNavController().navigate(NavMainDirections.goSearch())
                }
                //登录后显示头像
                LoginHelper.observerLogin(viewLifecycleOwner,
                    { showUserAvatar(UserManager.getCurUser()?.userInfo) })
            }
        }
    }

    private fun showUserAvatar(userInfo: UserInfo?) {
        toolbar?.let { bar ->
            if (bar is AppBarView) {
                userInfo?.let {
                    bar.setRightIconLetter(
                        ColorDrawable(
                            ActivityCompat.getColor(
                                requireContext(),
                                R.color.purple_200
                            )
                        ), it.nickname
                    )
                } ?: bar.setRightIconLetter(
                    ActivityCompat.getDrawable(
                        requireContext(),
                        R.drawable.icon_user_default
                    )!!, null
                )
            }
        }

    }

    protected fun setTitle(title: String) {
        toolbar?.let {
            if (it is AppBarView) {
                it.setTitle(title)
            } else {
                it.title = title
            }
        }
    }
}