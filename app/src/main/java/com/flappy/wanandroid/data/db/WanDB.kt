package com.flappy.wanandroid.data.db

import android.app.Application

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 18:08 2023/2/8
 */
object WanDB {
    private lateinit var context: Application
    private var _db: RoomDB? = null
    val dataBase: RoomDB
        get() = createDB()

    fun init(context: Application) {
        this.context = context
    }

    private fun createDB(): RoomDB {
        if (_db?.isOpen == true) return _db!!
        _db = RoomDB.buildDataBase(context)
        return _db!!
    }
}