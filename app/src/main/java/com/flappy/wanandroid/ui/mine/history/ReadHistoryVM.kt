package com.flappy.wanandroid.ui.mine.history

import androidx.lifecycle.LiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.ReadHistory
import com.flappy.wanandroid.data.repository.MineRepository
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 18:27 2023/2/8
 */
class ReadHistoryVM : BaseViewModel() {
    private val _histories = UnPeekLiveData<List<ReadHistory>>()
    val histories: LiveData<List<ReadHistory>>
        get() = _histories

    init {
        launch {
            _histories.value = MineRepository.getReadHistoryList(0, 100)

        }
    }
}