package com.flappy.wanandroid.ui.todo

import android.util.Log
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.flappy.util.px
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseToolbarFragment
import com.flappy.wanandroid.data.model.TODO_TYPE_JUST_THIS
import com.flappy.wanandroid.data.model.TODO_TYPE_LIFE
import com.flappy.wanandroid.data.model.TODO_TYPE_STUDY
import com.flappy.wanandroid.data.model.TODO_TYPE_WORK
import com.flappy.wanandroid.data.model.Todo
import com.flappy.wanandroid.databinding.FragmentTodoBinding
import com.flappy.wanandroid.databinding.LayoutNotLoginBinding
import com.flappy.wanandroid.ui.paging.asMergedLoadStates
import com.flappy.wanandroid.util.login.LoginHelper
import com.flappy.wanandroid.util.login.LoginIntercept
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:一级页面待办
 * @Date: Created in 22:20 2022/11/8
 */
@AndroidEntryPoint
class TodoFragment : BaseToolbarFragment<FragmentTodoBinding>() {
    private val todoAdapter: TodoListAdapter by lazy { TodoListAdapter() }
    private val doneAdapter: TodoListAdapter by lazy { TodoListAdapter() }
    private var notLoginBinding: LayoutNotLoginBinding? = null
    private var curType: Int = TODO_TYPE_JUST_THIS
    private val decoration by lazy {
        MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL).apply {
            dividerInsetStart = 68.px
        }
    }
    private val viewModel: TodoVM by hiltNavGraphViewModels(R.id.todo)
    private val itemClickListener = object : TodoListAdapter.ItemClickListener {
        override fun toggleState(todo: Todo, position: Int) {
            toggleTodoStatus(todo, position)
        }

        override fun showDetail(todo: Todo, position: Int) {
            goDetail(todo)
        }

        override fun delete(todo: Todo, position: Int) {
            viewModel.deleteTodo(todo.id)
        }
    }


    fun bindViewModel() {
        Log.d("fffff", hashCode().toString())
        viewModel.needRefresh.observe(this) {
            if (it) requestTodoList()
        }
    }

    override fun initView() {
        if (LoginHelper.isLogin()) {
            showTodoList()
        } else {
            showNeedLogin()
        }
        //监听登录、登出
        LoginHelper.observerLogin(viewLifecycleOwner, { showTodoList() }, { showNeedLogin() })

        //设置单选模式
        binding.cgType.isSingleSelection = true

        initListener()
        bindViewModel()
    }

    private fun initListener() {
        binding.swipeRefresh.setOnRefreshListener {
            todoAdapter.refresh()
            doneAdapter.refresh()
        }
        notLoginBinding?.btGoLogin?.setOnClickListener {
            goLogin()
        }
        //将箭头的点击区域扩展
        binding.layoutDone.setOnClickListener { binding.animationArrow.performClick() }
        //监听已完成模块的收缩和展开
        binding.animationArrow.setOnStateChangeListener { isExpand -> if (isExpand) showDoneList() else hideDoneList() }
        binding.cgType.setOnCheckedStateChangeListener { _, checkedIds ->
            val newType = when (checkedIds[0]) {
                R.id.chip_all -> TODO_TYPE_JUST_THIS
                R.id.chip_work -> TODO_TYPE_WORK
                R.id.chip_study -> TODO_TYPE_STUDY
                else -> TODO_TYPE_LIFE
            }
            //选择了新的类型后，刷新todo列表数据
            if (curType != newType) {
                curType = newType
                requestTodoList()
            }
        }
        binding.fabAddTodo.setOnClickListener {
            findNavController().navigate(
                TodoFragmentDirections.actionTodoFragmentToTodoDetailDialog(
                    curType
                )
            )
        }
    }

    private fun showNeedLogin() {
        binding.swipeRefresh.visibility = View.GONE
        binding.fabAddTodo.visibility = View.GONE
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
        binding.fabAddTodo.visibility = View.VISIBLE
        if (binding.notLogin.isInflated) {
            binding.notLogin.viewStub?.visibility = View.GONE
        }
        initRecyclerView()
        //确认登录状态后，请求todo列表
        requestTodoList()
        lifecycleScope.launch {
            todoAdapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }

            todoAdapter.loadStateFlow.asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvTodo.scrollToPosition(0) }

            doneAdapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }

            doneAdapter.loadStateFlow.asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvDone.scrollToPosition(0) }
        }
    }

    private fun goLogin() {
        //拦截到登录成功，继续请求todo列表
        LoginIntercept.get().checkLogin({ LoginHelper.goLogin(this) }) {
            notLoginBinding?.root?.visibility = View.GONE
            binding.swipeRefresh.visibility = View.VISIBLE
            requestTodoList()
        }
    }

    private fun requestTodoList() {

        lifecycleScope.launch {
            //请求未完成todo
            viewModel.getTodoPageSource(curType).collectLatest {
                todoAdapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            viewModel.getDonePageSource(curType).collectLatest {
                doneAdapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        doneAdapter.clickListener = itemClickListener
        todoAdapter.clickListener = itemClickListener

        binding.rvTodo.apply {
            if (itemDecorationCount == 0) {
                addItemDecoration(decoration)
            }
            adapter = todoAdapter
        }

        binding.rvDone.apply {
            if (itemDecorationCount == 0) {
                addItemDecoration(decoration)
            }
            adapter = doneAdapter
        }
    }

    override fun getLayoutId() = R.layout.fragment_todo


    private fun showDoneList() {
        binding.rvDone.visibility = View.VISIBLE
    }

    private fun hideDoneList() {
        binding.rvDone.visibility = View.GONE
    }

    private fun toggleTodoStatus(todo: Todo, position: Int) {
        viewModel.updateDoneStatus(todo.id, if (todo.status == 0) 1 else 0)
    }

    private fun goDetail(todo: Todo) {
        findNavController().navigate(
            TodoFragmentDirections.actionTodoFragmentToTodoDetailDialog(
                curType,
                todo
            )
        )
    }
}