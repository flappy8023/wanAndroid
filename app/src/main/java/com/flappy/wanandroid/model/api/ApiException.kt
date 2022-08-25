package com.flappy.wanandroid.model.api

/**
 * @Author: luweiming
 * @Description: 接口异常定义
 * @Date: Created in 16:37 2022/8/22
 */
data class ApiException(var code: Int, var msg: String) : RuntimeException()