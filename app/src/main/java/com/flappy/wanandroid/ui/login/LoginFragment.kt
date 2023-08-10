package com.flappy.wanandroid.ui.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.LoginFragmentBinding
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:登录页面
 * @Date: Created in 21:49 2022/10/27
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>() {
    private val viewModel by viewModels<LoginVM>()
    fun bindViewModel() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            parseState(it, {
                binding.btGoLogin.hideProgress(getString(R.string.login_success))
                findNavController().popBackStack()
            }, { msg ->
                //登陆失败，提示
                binding.btGoLogin.hideProgress("登录")
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun initView() {
        bindViewModel()
        bindProgressButton(binding.btGoLogin)
        binding.btGoLogin.setOnClickListener {
            binding.btGoLogin.showProgress {
                buttonText = getString(R.string.logining)
            }
            doLogin(binding.etUsername.text.toString(), binding.etPwd.text.toString())
        }
    }

    private fun doLogin(userName: String, pwd: String) {
        viewModel.login(userName, pwd)
    }

    override fun getLayoutId() = R.layout.login_fragment
}