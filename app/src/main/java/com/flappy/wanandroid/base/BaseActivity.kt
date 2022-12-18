package com.flappy.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @Author: luweiming
 * @Description:activity基类，使用时需要指定ViewDataBinding和ViewModel
 * @Date: Created in 12:48 2022/8/21
 */
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        handleArguments()
        observe()
        initView()
    }

    open fun handleArguments() {

    }


    /**
     * 初始化viewBinding
     */
    private fun initViewBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
    }

    /**
     *在此处订阅viewModel中的数据变化
     */
    abstract fun observe()

    /**
     * 初始化视图
     */
    abstract fun initView()

    /**
     * 获取布局文件资源id
     * @return Int
     */
    abstract fun getLayoutId(): Int

}