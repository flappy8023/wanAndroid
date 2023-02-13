package com.flappy.wanandroid.data.model

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:06 2022/8/23
 */
data class SquareArticle(
    var adminAdd: Boolean,
    var apkLink: String,
    var audit: Int,
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
//    var isAdminAdd:Boolean
    var link: String,
    var niceDate: String,
    var niceShareDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var realSuperChapterId: Long,
    var selfVisible: Int,
    var shareDate: String,
    var shareUser: String,
    var superChapterId: Long,
    var superChapterName: String,
    var tags: List<ArticleTag>,
    var title: String,
    var type: Int,
    var userId: Long,
    var visible: Int,
    var zan: Long
)

data class UserArticles(var coinInfo: CoinRankItem, var shareArticles: PagedData<Article>)