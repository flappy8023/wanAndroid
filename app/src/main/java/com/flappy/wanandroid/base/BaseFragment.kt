package com.flappy.wanandroid.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
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
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:49 2022/8/30
 */
abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {
    lateinit var binding: VB
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleArguments()
    }

    /**
     * 处理传参
     */
    open fun handleArguments() {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
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


    abstract fun initView()

    abstract fun getLayoutId(): Int

    protected fun setTitle(title: String) {
        toolbar?.let {
            if (it is AppBarView) {
                it.setTitle(title)
            } else {
                it.title = title
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}