package com.flappy.wanandroid.data.model

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:55 2022/11/10
 */

/**
 *
 *
 * @property completeDate
 * @property completeDateStr
 * @property content
 * @property date
 * @property dateStr
 * @property id
 * @property priority
 * @property status 状态， 1-完成；0未完成; 默认全部展示；
 * @property title
 * @property type
 * @property userId
 */
data class Todo(
    val completeDate: Long?,
    val completeDateStr: String?,
    val content: String,
    val date: Long,
    val dateStr: String,
    val id: Long,
    val priority: Int,
    val status: Int,
    val title: String,
    val type: Int,
    val userId: Long
)