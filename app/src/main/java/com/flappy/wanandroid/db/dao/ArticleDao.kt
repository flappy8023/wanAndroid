package com.flappy.wanandroid.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flappy.wanandroid.vo.Article

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:37 2022/9/1
 */

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

    @Query("SELECT * FROM article ORDER BY publishTime")
    fun getArticles(): PagingSource<Int, Article>

    @Query("delete from article")
    fun deleteAll()
}