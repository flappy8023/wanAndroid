package com.flappy.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * @Author: luweiming
 * @Description:activity基类，使用时需要指定ViewDataBinding和ViewModel
 * @Date: Created in 12:48 2022/8/21
 */
abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    lateinit var mViewBinding: VB
    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViewBinding()
        initViewModel()
        observe()
        initView()
    }


    /**
     * 初始化viewBinding
     */
    private fun initViewBinding() {
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewBinding.lifecycleOwner = this
    }

    /**
     * 初始化viewModel
     */
    private fun initViewModel() {
        mViewModel =
            ViewModelProvider(this).get((mViewModel.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>)
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