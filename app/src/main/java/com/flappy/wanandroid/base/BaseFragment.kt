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
import com.flappy.wanandroid.data.api.ApiResult
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
        initView()
    }




    abstract fun initView()

    abstract fun getLayoutId(): Int




    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun <T> parseState(
        apiResult: ApiResult<T>,
        success: (T) -> Unit = {},
        fail: (String) -> Unit = {}
    ) {
        when (apiResult) {
            is ApiResult.Success -> success(apiResult.data)
            is ApiResult.Failure -> fail(apiResult.exception.errMsg)
        }
    }

}