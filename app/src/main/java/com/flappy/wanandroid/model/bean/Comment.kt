package com.flappy.wanandroid.model.bean

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 13:31 2022/8/24
 */
data class Comment(
    var anonymous: Int,
    var appendForContent: Int,
    var articleId: Long,
    var canEdit: Boolean,
    var content: String,
    var contentMd: String,
    var id:Long,
    var niceDate:String,
    var publishDate:String,
    var replyCommentId:Long,
    var replyComments:List<Any>,
    var rootCommentId:Long,
    var status:Int,
    var toUserId:Long,
    var toUserName:String,
    var userId:Long,
    var userName:String,
    var zan:Long
)