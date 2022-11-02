package com.flappy.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flappy.wanandroid.R
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
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
    }

    open fun initViewModel() {
        val model =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        viewModel = ViewModelProvider(this).get(model)
    }

    abstract fun bindViewModel()

    abstract fun initView()

    abstract fun getLayoutId(): Int
}