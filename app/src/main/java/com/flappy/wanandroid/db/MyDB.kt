package com.flappy.wanandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flappy.wanandroid.vo.Article
import com.flappy.wanandroid.vo.ArticleKey
import com.flappy.wanandroid.db.dao.ArticleDao
import com.flappy.wanandroid.db.dao.ArticleKeyDao

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 11:02 2022/9/1
 */
@Database(entities = [Article::class,ArticleKey::class], version = 1)
abstract class MyDB : RoomDatabase() {
    companion object {

         fun buildDataBase(context: Context): MyDB {
            return Room.databaseBuilder(context,MyDB ::class.java,"wan.db").build()
        }
    }

    abstract fun articleDao(): ArticleDao
    abstract fun articleKeyDao(): ArticleKeyDao
}