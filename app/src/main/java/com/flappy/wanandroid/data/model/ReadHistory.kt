package com.flappy.wanandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: luweiming
 * @Description: 阅读历史
 * @Date: Created in 15:03 2023/2/8
 */
@Entity(tableName = "readHistory")
class ReadHistory(
    @PrimaryKey val link: String,
    val title: String,
    val time: Long,
    @androidx.annotation.IntRange(
        from = MIN_PERCENT,
        to = MAX_PERCENT
    ) var percent: Int
) {
    companion object {
        const val MIN_PERCENT = 0L
        const val MAX_PERCENT = 10000L
    }
}