package com.flappy.wanandroid.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flappy.wanandroid.data.model.ArticleKey

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:36 2022/9/1
 */
@Dao
interface ArticleKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(key: ArticleKey)

    @Query("DELETE FROM article_key WHERE category = :category")
    suspend fun deleteByCategory(category:String)

    @Query("SELECT * FROM article_key WHERE category = :category")
    suspend fun getByCategory(category: String): ArticleKey

}