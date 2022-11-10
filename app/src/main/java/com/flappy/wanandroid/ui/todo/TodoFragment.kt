package com.flappy.wanandroid.ui.todo

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentTodoBinding
import com.flappy.wanandroid.ext.px
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.flow.collectLatest

/**
 * @Author: luweiming
 * @Description:一级页面待办
 * @Date: Created in 22:20 2022/11/8
 */
class TodoFragment:BaseFragment<FragmentTodoBinding,TodoVM>() {
    private val adapter = TodoListAdapter()
    override fun bindViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.todoList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val decoration = MaterialDividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        decoration.dividerInsetStart = 68.px
        binding.rvTodo.addItemDecoration(decoration)
        binding.rvTodo.adapter = adapter
    }

    override fun getLayoutId() = R.layout.fragment_todo
}