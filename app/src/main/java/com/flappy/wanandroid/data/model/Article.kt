package com.flappy.wanandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: luweiming
 * @Description:首页相关接口响应数据定义
 * @Date: Created in 17:00 2022/8/22
 */

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
    var tag: List<ArticleTag>?,
    var title: String,
    var type: Int,
    var userId: Long,
    var visible: Int,
    var zan: Long,
    var indexInResponse: Int = -1,
    @PrimaryKey(autoGenerate = true)
    var aId: Int = 0
)

data class ArticleTag(var url: String, var name: String)

@Entity
data class BannerItem(
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


data class TreeItem(
    var articleList: List<Article>,
    var author: String,
    var children: List<TreeItem>,
    var courseId: Long,
    var cover: String,
    var desc: String,
    var id: Long,
    var lisense: String,
    var lisenseLink: String,
    var name: String,
    var order: Long,
    var parentChapterId: Long,
    var type: Int,
    var userControlSetTop: Boolean,
    var visible: Int
)

