package com.flappy.wanandroid.ui.todo

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.FragmentTodoBinding
import com.flappy.wanandroid.databinding.LayoutNotLoginBinding
import com.flappy.wanandroid.ext.px
import com.flappy.wanandroid.util.login.LoginHelper
import com.flappy.wanandroid.util.login.LoginIntercept
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.flow.collectLatest

/**
 * @Author: luweiming
 * @Description:一级页面待办
 * @Date: Created in 22:20 2022/11/8
 */
class TodoFragment : BaseFragment<FragmentTodoBinding, TodoVM>() {
    private val adapter = TodoListAdapter()
    private var notLoginBinding: LayoutNotLoginBinding? = null
    override fun bindViewModel() {
    }

    override fun initView() {
        if (LoginHelper.isLogin()) {
            showTodoList()
        } else {
            showNeedLogin()
        }
        notLoginBinding?.btGoLogin?.setOnClickListener {
            goLogin()
        }
        //监听登录、登出
        LoginHelper.observerLogin(viewLifecycleOwner, { showTodoList() }, { showNeedLogin() })

    }

    private fun showNeedLogin() {
        binding.swipeRefresh.visibility = View.GONE
        if (binding.notLogin.isInflated) {
            binding.notLogin.viewStub?.visibility = View.VISIBLE
        } else {
            binding.notLogin.viewStub?.setOnInflateListener { _, view ->
                notLoginBinding = LayoutNotLoginBinding.bind(view)
            }
            binding.notLogin.viewStub?.inflate()
        }
    }

    private fun showTodoList() {
        binding.swipeRefresh.visibility = View.VISIBLE
        if (binding.notLogin.isInflated) {
            binding.notLogin.viewStub?.visibility = View.GONE
        }
        initRecyclerView()
        //确认登录状态后，请求todo列表
        requestTodoList()
    }

    private fun goLogin() {
        //拦截到登录成功，继续请求todo列表
        LoginIntercept.get().checkLogin({ LoginHelper.goLogin(this) }) {
            requestTodoList()
        }
    }

    private fun requestTodoList() {
        notLoginBinding?.root?.visibility = View.GONE
        binding.swipeRefresh.visibility = View.VISIBLE
        lifecycleScope.launchWhenCreated {
            viewModel.todoList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        val decoration =
            MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        decoration.dividerInsetStart = 68.px
        binding.rvTodo.addItemDecoration(decoration)
        binding.rvTodo.adapter = adapter
    }

    override fun getLayoutId() = R.layout.fragment_todo
}