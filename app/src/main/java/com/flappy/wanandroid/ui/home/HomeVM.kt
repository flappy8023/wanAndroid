package com.flappy.wanandroid.ui.home

import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.api.ApiManager
import com.flappy.wanandroid.api.ApiService
import com.flappy.wanandroid.db.MyDB
import com.flappy.wanandroid.repository.ArticleRepository

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:07 2022/8/30
 */
class HomeVM : BaseViewModel() {
    private val db: MyDB by lazy { MyDB.buildDataBase(MyApp.app) }
    private val api: ApiService by lazy { ApiManager.service }
    private val repository:ArticleRepository by lazy {
        ArticleRepository(api,db)
    }

    val articles = repository.getArticles(30)
}