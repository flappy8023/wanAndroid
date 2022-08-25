package com.flappy.wanandroid.model.bean

/**
 * @Author: luweiming
 * @Description:首页相关接口响应数据定义
 * @Date: Created in 17:00 2022/8/22
 */
data class ArticleListData(
    var curPage: Long,
    var datas: List<Article>,
    var offset: Long,
    var over: Boolean,
    var pageCount: Long,
    var size: Int,
    var total: Long
)

data class Article(
    var adminAdd: Boolean,
    var apkLink: String,
    var audit: String,
    var author: String,
    var canEdit: Boolean,
    var chapterId: Long,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Long,
    var desc: String,
    var descMd: String,
    var envelopePic: String,
    var fresh: Boolean,
    var host: String,
    var id: Long,
//    var isAdminAdd:Boolean,
    var link: String,
    var niceDate: String,
    var niceShareDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var realSuperChapterId: Long,
    var selfVisible: Int,
    var shareDate: Long,
    var shareUser: String,
    var superChapterId: Long,
    var superChapterName: String,
    var tag: List<ArticleTag>,
    var title: String,
    var type: Int,
    var userId: Long,
    var visible: Int,
    var zan: Long
)

data class ArticleTag(var url: String, var name: String)

data class Banner(
    var desc: String,
    var id: Long,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: Int,
    var url: String
)

data class CommonWebSite(
    var category: String,
    var icon: String,
    var id: Int,
    var link: String,
    var name: String,
    var order: Int,
    var visible: Int
)

data class HotKey(var id: Int, var link: String, var name: String, var order: Int, var visible: Int)

