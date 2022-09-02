package com.flappy.wanandroid.vo

/**
 * @Author: luweiming
 * @Description: 积分相关响应定义
 * @Date: Created in 13:40 2022/8/23
 */

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