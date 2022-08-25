package com.flappy.wanandroid.model.bean

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 18:30 2022/8/24
 */
class WXOfficialAccount(
    var articleList: List<Any>,
    var author: String,
    var children: List<Any>,
    var courseId: Long,
    var cover: String,
    var desc: String,
    var id:Long,
    var lisense:String,
    var lisenseLink:String,
    var name:String,
    var order:Long,
    var parentChapterId:Long,
    var type:Int,
    var userControlSetTop:Boolean,
    var visible:Int
)