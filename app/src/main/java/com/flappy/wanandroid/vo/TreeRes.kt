package com.flappy.wanandroid.vo

/**
 * @Author: luweiming
 * @Description:体系数据相应
 * @Date: Created in 17:51 2022/8/22
 */

data class TreeItem(
    var articleList: List<Article>,
    var author: String,
    var children: List<TreeItem>,
    var courseId: Long,
    var cover: String,
    var desc:String,
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