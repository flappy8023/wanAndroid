package com.flappy.wanandroid.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.ArticleKey
import com.flappy.wanandroid.data.db.dao.ArticleDao
import com.flappy.wanandroid.data.db.dao.ArticleKeyDao

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 11:02 2022/9/1
 */
@Database(entities = [Article::class, ArticleKey::class], version = 1)
abstract class MyDB : RoomDatabase() {
    companion object {

         fun buildDataBase(context: Context): MyDB {
            return Room.databaseBuilder(context, MyDB ::class.java,"wan.db").build()
        }
    }

    abstract fun articleDao(): ArticleDao
    abstract fun articleKeyDao(): ArticleKeyDao
}