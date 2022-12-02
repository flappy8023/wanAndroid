package com.flappy.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:50 2022/11/28
 */
abstract class BaseDialog<VB : ViewDataBinding, VM : BaseViewModel> : DialogFragment() {
    lateinit var binding: VB
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    open fun initViewModel() {
        val modelClass =
            (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        viewModel = ViewModelProvider(this).get(modelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
    }
}