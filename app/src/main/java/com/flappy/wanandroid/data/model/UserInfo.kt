package com.flappy.wanandroid.data.model

/**
 * @Author: luweiming
 * @Description:用户信息定义
 * @Date: Created in 13:19 2022/8/24
 */
data class UserInfoData(var coinInfo: CoinInfo, var collectArticleInfo: CollectArticleInfo, var userInfo: UserInfo)
data class CoinInfo(
    var coinCount: Long,
    var level: Int,
    var nickname: String,
    var username: String,
    var rank: String
)

data class CollectArticleInfo(var count: Long)
data class UserInfo(
    var admin: Boolean,
    var chapterTops: List<Any>,
    var coinCount: Long,
    var collectIds: List<Long>,
    var email: String,
    var icon: String,
    var id: Long,
    var nickname: String,
    var publicName: String,
    var type: Int,
    var username: String
)


data class CoinRankItem(
    var coinCount: Long,
    var level: Long,
    var rank: Long,
    var userId: Long,
    var userName: String
)

data class CoinRecordListData(
    var curPage: Int,
    var datas: List<CoinRecord>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Long
)

data class CoinRecord(
    var coinCount: Long,
    var date: Long,
    var desc: String,
    var id: Long,
    var reason: String,
    var type: Int,
    var userId: Long,
    var userName: String
)
