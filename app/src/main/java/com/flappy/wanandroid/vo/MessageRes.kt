package com.flappy.wanandroid.vo

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:07 2022/8/24
 */
data class Message(
    var category: Int,
    var date: Long,
    var fromUser: String,
    var fromUserId: Long,
    var fullLink: String,
    var id: Long,
    var isRead: Boolean,
    var link: String,
    var message: String,
    var niceDate:String,
    var tag:String,
    var title:String,
    var userId:Long
)