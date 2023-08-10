package com.flappy.wanandroid.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.Todo
import com.flappy.wanandroid.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:21 2022/11/8
 */
@HiltViewModel
class TodoVM @Inject constructor(private val todoRepository: TodoRepository) : BaseViewModel() {

    private val _needRefresh = MutableLiveData<Boolean>()
    val needRefresh: LiveData<Boolean>
        get() = _needRefresh
    private val _addState: MutableLiveData<TodoUIState> = MutableLiveData()
    val addState: LiveData<TodoUIState>
        get() = _addState
    private val _editState: MutableLiveData<TodoUIState> = MutableLiveData()
    val editState: LiveData<TodoUIState>
        get() = _editState

    fun getTodoPageSource(type: Int? = null) =
        todoRepository.todoListPager(0, type).flow.cachedIn(viewModelScope)

    fun getDonePageSource(type: Int? = null) =
        todoRepository.todoListPager(1, type).flow.cachedIn(viewModelScope)

    /**
     * 添加一个TODO
     *
     * @param title
     * @param content
     * @param date
     * @param type
     * @param priority 默认为2，一般优先级
     */
    fun addTodo(title: String?, content: String?, date: String, type: Int, priority: Int = 2) {
        if (title.isNullOrBlank()) {
            _addState.value =
                TodoUIState.Failure(MyApp.app.getString(R.string.todo_err_title_cant_be_empty))
            return
        }
        if (content.isNullOrBlank()) {
            _addState.value =
                TodoUIState.Failure(MyApp.app.getString(R.string.todo_err_content_cant_be_empty))
            return
        }
        _addState.value = TodoUIState.Loading
        launch {
            val result = todoRepository.addTodo(title, content, date, type, priority)
            if (result.isSuccess) {
                _addState.value = TodoUIState.Success
                //新增成功需要刷新列表
                _needRefresh.value = true
            } else {
                _addState.value = TodoUIState.Failure("")
            }
        }
    }

    fun updateDoneStatus(id: Long, status: Int) {
        launch {
            val result = todoRepository.updateTodoStatus(id, status)
            if (result.isSuccess) {
                _needRefresh.value = true
            }
        }
    }

    fun updateTodo(todo: Todo) {
        launch {
            val result = todoRepository.updateTodo(todo)
            if (result.isSuccess) {
                _needRefresh.value = true
                _editState.value = TodoUIState.Success
            }
        }
    }

    fun deleteTodo(id: Long) {
        _editState.value = TodoUIState.Loading
        launch {
            val result = todoRepository.deleteTodo(id)
            if (result.isSuccess) {
                _needRefresh.value = true
            }
        }
    }
}