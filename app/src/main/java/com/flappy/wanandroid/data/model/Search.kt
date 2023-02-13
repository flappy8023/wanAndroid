package com.flappy.wanandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:21 2023/2/8
 */
/**
 * 搜索热词
 *
 * @property id
 * @property link
 * @property name
 * @property order
 * @property visible
 */
data class HotKey(val id: Int, val link: String, val name: String, val order: Int, val visible: Int)

/**
 * 搜索历史
 *
 */
@Entity
data class SearchHistory(@PrimaryKey val keyWord: String, val time: Long)
