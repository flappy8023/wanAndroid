package com.flappy.wanandroid.ui.web

import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.db.WanDB
import com.flappy.wanandroid.data.model.ReadHistory

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:52 2022/9/8
 */
class WebVM : BaseViewModel() {

    fun addReadHistory(link: String, title: String, percent: Float = 0f) {
        launch {
            val time = System.currentTimeMillis()
            WanDB.dataBase.readHistoryDao().insert(ReadHistory(link, title, time, 0))
        }
    }
}