package com.flappy.wanandroid.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flappy.wanandroid.data.db.dao.ReadHistoryDao
import com.flappy.wanandroid.data.db.dao.SearchHistoryDao
import com.flappy.wanandroid.data.model.ReadHistory
import com.flappy.wanandroid.data.model.SearchHistory

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 11:02 2022/9/1
 */
@Database(entities = [ReadHistory::class, SearchHistory::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    companion object {

        fun buildDataBase(context: Context): RoomDB {
            return Room.databaseBuilder(context, RoomDB::class.java, "wan.db").build()
        }
    }

    abstract fun readHistoryDao(): ReadHistoryDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}