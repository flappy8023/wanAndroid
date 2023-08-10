package com.flappy.wanandroid.ui.mine.history

import androidx.lifecycle.LiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.ReadHistory
import com.flappy.wanandroid.data.repository.MineRepository
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 18:27 2023/2/8
 */
@HiltViewModel
class ReadHistoryVM @Inject constructor(val mineRepository: MineRepository) : BaseViewModel() {
    private val _histories = UnPeekLiveData<List<ReadHistory>>()
    val histories: LiveData<List<ReadHistory>>
        get() = _histories

    init {
        launch {
            _histories.value = mineRepository.getReadHistoryList(0, 100)

        }
    }
}