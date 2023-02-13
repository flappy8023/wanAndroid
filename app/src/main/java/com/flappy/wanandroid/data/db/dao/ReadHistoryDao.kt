package com.flappy.wanandroid.data.db.dao

import androidx.room.*
import com.flappy.wanandroid.data.model.ReadHistory

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:27 2023/2/8
 */
@Dao
interface ReadHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(readHistory: ReadHistory)

    @Query("DELETE FROM readHistory WHERE link =:link")
    suspend fun delete(link: String)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(readHistory: ReadHistory)

    @Query("DELETE FROM readHistory")
    suspend fun deleteAll()

    @Query("SELECT * FROM readHistory ORDER BY time DESC LIMIT :offset,:count")
    suspend fun getAll(offset: Int, count: Int): List<ReadHistory>


    @Query("SELECT * FROM readHistory WHERE link = :link LIMIT 1")
    suspend fun getReadHistoryByLink(link: String): ReadHistory?

    @Query("DELETE FROM readHistory WHERE link NOT IN (SELECT link FROM readHistory ORDER BY time DESC LIMIT 0,:maxCount)")
    suspend fun deleteOverCountLimit(maxCount: Int)

}