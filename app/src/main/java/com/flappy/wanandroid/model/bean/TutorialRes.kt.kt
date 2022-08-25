package com.flappy.wanandroid.model.bean

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:42 2022/8/24
 */
data class Tutorial(
    var author: String,
    var children: List<Any>,
    var courseId: Long,
    var cover: String,
    var desc: String,
    var id: Long,
    var lisense: String,
    var lisenseLink: String,
    var name:String,
    var order:Long,
    var parentChapterId:Long,
    var userControlSetTop:Boolean,
    var visible:Int
)