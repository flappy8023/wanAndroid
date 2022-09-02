package com.flappy.wanandroid.vo

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 13:19 2022/8/24
 */
data class UserInfoData(var coinInfo: CoinInfo,var collectArticleInfo:CollectArticleInfo,var userInfo:UserInfo)
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
    var type:Int,
    var username:String
)
