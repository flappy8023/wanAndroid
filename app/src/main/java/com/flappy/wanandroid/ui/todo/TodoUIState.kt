package com.flappy.wanandroid.ui.todo

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:49 2022/12/1
 */
sealed class TodoUIState {
    object Loading : TodoUIState()
    object Success : TodoUIState()
    data class Failure(val msg: String) : TodoUIState()

}