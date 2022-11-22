package com.flappy.wanandroid.base

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.NavMainDirections
import com.flappy.wanandroid.R
import com.flappy.wanandroid.data.model.UserInfo
import com.flappy.wanandroid.ui.search.SearchActivity
import com.flappy.wanandroid.util.UserManager
import com.flappy.wanandroid.util.login.LoginHelper
import com.flappy.widget.AppBarView
import java.lang.reflect.ParameterizedType

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:49 2022/8/30
 */
abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    lateinit var binding: VB
    lateinit var viewModel: VM
    private var toolbar: AppBarView? = null
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
        initViewModel()
        bindViewModel()
        initToolbar()
        initView()
        return binding.root
    }

    private fun initToolbar() {
        toolbar = binding.root.findViewById(R.id.toolbar)
        toolbar?.let {
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
            it.setTitle(getString(R.string.app_name))

            //展示用户头像
            if (null != UserManager.getCurUser()?.userInfo) {
                showUserAvatar(UserManager.getCurUser()?.userInfo!!)
            }
            it.setRightClickListener {
                findNavController().navigate(NavMainDirections.actionGlobalMine())
            }
            it.setLeftClickListener {
                requireContext().startActivity(Intent(requireContext(), SearchActivity::class.java))
            }
            //登录后显示头像
            LoginHelper.observerLogin(viewLifecycleOwner,
                { showUserAvatar(UserManager.getCurUser()?.userInfo) })
        }
    }

    private fun showUserAvatar(userInfo: UserInfo?) {
        userInfo?.let {
            toolbar?.setRightIconLetter(
                ColorDrawable(
                    ActivityCompat.getColor(
                        requireContext(),
                        R.color.purple_200
                    )
                ), it.nickname
            )
        } ?: toolbar?.setRightIconLetter(
            ActivityCompat.getDrawable(
                requireContext(),
                R.drawable.icon_user_default
            )!!, null
        )
    }

    @Suppress("UNCHECKED_CAST")
    open fun initViewModel() {
        val model =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        viewModel = ViewModelProvider(this).get(model)
    }

    abstract fun bindViewModel()

    abstract fun initView()

    abstract fun getLayoutId(): Int

    protected fun setTitle(title: String) {
        toolbar?.setTitle(title)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}