package com.flappy.wanandroid.ui.login

import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.LoginFragmentBinding
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress

/**
 * @Author: luweiming
 * @Description:登录页面
 * @Date: Created in 21:49 2022/10/27
 */
class LoginFragment : BaseVMFragment<LoginFragmentBinding, LoginVM>() {
    override fun bindViewModel() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            if (it) {
                binding.btGoLogin.hideProgress(getString(R.string.login_success))
                findNavController().popBackStack()
            }
        }
    }

    override fun initView() {
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