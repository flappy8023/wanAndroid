package com.flappy.wanandroid.data.model

/**
 * @Author: luweiming
 * @Description: 分页数据响应的基类
 * @Date: Created in 14:07 2022/8/23
 */
data class PagedData<T> (var curPage:Int,var datas:MutableList<T>,var offset:Int,var over:Boolean,var pageCount:Int,var size:Int,var total:Long)