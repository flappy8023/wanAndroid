package com.flappy.wanandroid.ui.todo

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentTodoBinding

/**
 * @Author: luweiming
 * @Description:一级页面待办
 * @Date: Created in 22:20 2022/11/8
 */
class TodoFragment:BaseFragment<FragmentTodoBinding,TodoVM>() {
    override fun bindViewModel() {
    }

    override fun initView() {
    }

    override fun getLayoutId() = R.layout.fragment_todo
}