package com.flappy.wanandroid.api

import com.flappy.wanandroid.vo.*
import retrofit2.http.*

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 20:36 2022/8/20
 */
interface ApiService {
    companion object {
        const val BASE_URL: String = "https://www.wanandroid.com"
    }

    /**
     * 1.1 首页文章列表
     * @param pageNo Int
     * @param pageSize Int
     */
    @GET("article/list/{pageNo}/json")
    suspend fun getHomeArticleList(
        @Path("pageNo") pageNo: Int,
        @Query("pageSize") pageSize: Int
    ): Response<PagedData<Article>>


    /**
     * 1.2 首页banner
     * @return Response<List<Banner>>
     */
    @GET("banner/json")
    suspend fun getBanners(): Response<List<BannerItem>>

    /**
     * 1.3 常用网站
     * @return Response<List<CommonWebSite>>
     */
    @GET("friend/json")
    suspend fun getCommonlyUsedWebsites(): Response<List<CommonWebSite>>

    /**
     * 1.4 搜索热词
     * @return Response<List<HotKey>>
     */
    @GET("hotkey/json")
    suspend fun getHotKey(): Response<List<HotKey>>

    /**
     * 1.5 置顶文章
     * @return Response<List<Article>>
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): Response<List<Article>>

    /**
     * 2.1 体系数据
     * @return Response<List<TreeItem>>
     */
    @GET("tree/json")
    suspend fun getTrees(): Response<List<TreeItem>>

    /**
     * 2.2 知识体系下的文章
     * @param cid Long
     * @param pageSize Int
     */
    @GET("article/list/0/json?cid={cid}&page_size={pageSize}")
    suspend fun getArticlesOfTree(
        @Path("cid") cid: Long,
        @Path("pageSize") pageSize: Int
    ): Response<PagedData<Article>>

    /**
     * 2.3 按照作者昵称搜索文章
     * @param author String
     */
    @GET("article/list/0/json?author={author}")
    suspend fun getArticlesByAuthor(@Path("author") author: String): Response<PagedData<Article>>

    /**
     * 3.1 导航数据
     * @return Response<List<NaviBean>>
     */
    @GET("navi/json")
    suspend fun getNaviData(): Response<List<NaviBean>>

    /**
     * 4.1 项目分类
     * @return Response<List<TreeItem>>
     */
    @GET("project/tree/json")
    suspend fun getProjectCategory(): Response<List<TreeItem>>

    /**
     * 4.2 项目列表数据
     * @param page Int
     * @param cid Long 分类的id
     * @param pageSize Int
     */
    @GET("project/list/{page}/json?cid={cid}&page_size={pageSize}")
    suspend fun getProjectList(
        @Path("page") page: Int,
        @Path("cid") cid: Long,
        @Path("pageSize") pageSize: Int
    ): Response<PagedData<Article>>


    /**
     * 5.1 登录
     * @param name String
     * @param pwd String
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") name: String, @Field("password") pwd: String): Response<*>

    /**
     * 5.2 注册
     * @param name String
     * @param pwd String
     * @param  [ERROR : Type annotation was missing for parameter <no name provided>]
     */
    @POST("user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") name: String,
        @Field("password") pwd: String,
        @Field("repassword") repwd: String
    ): Response<Any>

    /**
     * 5.3 退出
     * @return Response<Any>
     */
    @GET("user/logout/json")
    suspend fun logout(): Response<Any>

    /**
     * 6.1 收藏文章列表
     * @param pageNo Int
     * @param pageSize Int
     */
    @GET("lg/collect/list/{pageNo}/json?pageSize={pageSize}")
    suspend fun getCollectList(
        @Path("pageNo") pageNo: Int,
        @Path("pageSize") pageSize: Int
    ): Response<PagedData<Article>>

    /**
     * 6.2 收藏站内文章
     * @param id Long
     */
    @POST("/lg/collect/{id}/json")
    @FormUrlEncoded
    suspend fun collectInnerArticle(@Path("id") id: Long)

    /**
     * 6.3 收藏站外文章
     * @param title String
     * @param author String
     * @param link String
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    suspend fun collectOuterArticle(
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("link") link: String
    )

    /**
     * 更新收藏的文章
     * @param author String
     * @param link String
     * @param title String
     */
    @POST("lg/collect/user_article/update/{id}/json")
    @FormUrlEncoded
    suspend fun updateCollectedArticle(
        @Field("author") author: String,
        @Field("link") link: String,
        @Field("title") title: String
    )

    /**
     * 6.4.1 从文章列表取消收藏
     * @param id Long
     */
    @POST("lg/uncollect_originId/{id}/json")
    @FormUrlEncoded
    suspend fun unCollectFromArticleList(@Path("id") id: Long)

    /**
     * 6.4.2 从我的收藏取消收藏
     * @param id Long
     * @param originId Long
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    suspend fun unCollectFromMyCollect(@Path("id") id: Long, @Field("originId") originId: Long = -1)

    /**
     * 6.5 收藏网站列表
     */
    @GET("lg/collect/usertools/json")
    suspend fun getCollectedWebsiteList(): Response<List<WebSiteBean>>

    /**
     * 6.6 收藏网址
     * @param name String
     * @param link String
     */
    @POST("lg/collect/addtool/json")
    @FormUrlEncoded
    suspend fun collectWebsite(@Field("name") name: String, @Field("link") link: String)

    /**
     * 6.7 编辑收藏网站
     * @param id Long
     * @param name String
     * @param link String
     */
    @POST("lg/collect/updatetool/json")
    @FormUrlEncoded
    suspend fun updateCollectedWebsite(
        @Field("id") id: Long,
        @Field("name") name: String,
        @Field("link") link: String
    )

    /**
     * 6.8 删除收藏网站
     * @param id Long
     */
    @POST("lg/collect/deletetool/json")
    @FormUrlEncoded
    suspend fun deleteCollectedArticle(@Field("id") id: Long)

    /**
     * 7.1 搜索
     * @param pageNo Int
     * @param pageSize Int
     * @param keyword String 关键词，多个可以用空格隔开
     */
    @FormUrlEncoded
    @POST("article/query/{pageNo}/json?page_size={pageSize}")
    suspend fun searchArticle(
        @Path("pageNo") pageNo: Int,
        @Path("pageSize") pageSize: Int,
        @Field("k") keyword: String
    )

    /**
     * 8.1 新增一个TODO
     * @param title String
     * @param content String
     * @param date String? yyyy-MM-dd格式，不传默认当天
     * @param type Int 类型，大于0的整数，app预定义
     * @param priority Int 优先级，大于0的整数，app预定义
     */
    @POST("lg/todo/add/json")
    @FormUrlEncoded
    suspend fun addTODO(
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("date") date: String?,
        @Field("type") type: Int,
        @Field("priority") priority: Int
    )

    /**
     * 8.2 更新TODO
     * @param id Long
     * @param title String
     * @param content String
     * @param date String yyyy-MM-dd
     * @param status Int 0或1，传1代表未完成到已完成，反之则反之
     * @param type Int
     * @param priority Int
     */
    @POST("lg/todo/update/{id}/json")
    @FormUrlEncoded
    suspend fun updateTODO(
        @Path("id") id: Long,
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("date") date: String,
        @Field("status") status: Int,
        @Field("type") type: Int,
        @Field("priority") priority: Int
    )

    /**
     * 8.3 删除TODO
     * @param id Long
     */
    @POST("lg/todo/delete/{id}/json")
    @FormUrlEncoded
    suspend fun deleteTODO(@Path("id") id: Long)


    /**
     * 8.4 仅更新完成状态Todo
     * @param id Long
     * @param status Int 0或1，传1代表未完成到已完成，反之则反之
     */
    @POST("lg/todo/done/{id}/json")
    @FormUrlEncoded
    suspend fun updateTODOStatus(@Path("id") id: Long, @Field("status") status: Int)

    /**
     * 8.5 TODO列表
     * @param pageNo Int 从1开始
     * @param status Int?
     * @param type Int?
     * @param priority Int
     * @param orderby Int 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
     */
    @GET("lg/todo/v2/list/{pageNo}/json")
    suspend fun getTODOList(
        @Path("pageNo") pageNo: Int,
        @Query("status") status: Int?,
        @Query("type") type: Int?,
        @Query("priority") priority: Int,
        @Query("orderby") orderby: Int = 4
    )

    /**
     * 9.1 积分排行榜
     * @param pageNo Int
     */
    @GET("coin/rank/{pageNo}/json")
    suspend fun getCoinRankList(@Path("pageNo") pageNo: Int): Response<PagedData<CoinRankItem>>

    /**
     * 9.2 获取个人积分，需要登录
     * @return Response<CoinRankItem>
     */
    @GET("lg/coin/userinfo/json")
    suspend fun getMyCoin(): Response<CoinRankItem>


    /**
     * 9.3 获取个人积分获取列表，需要登陆
     * @param pageNo Int
     */
    @GET("lg/coin/list/{pageNo}/json")
    suspend fun getCoinRecordList(@Path("pageNo") pageNo: Int): Response<PagedData<CoinRecord>>

    /**
     * 10.1 广场列表数据
     * @param pageNo Int 从0开始
     * @param pageSize Int
     */
    @GET("user_article/list/{pageNo}/json")
    suspend fun getSquareArticleList(
        @Path("pageNO") pageNo: Int,
        @Query("page_size") pageSize: Int
    ): Response<PagedData<SquareArticle>>


    /**
     * 10.2 分享人对应列表数据
     * @param pageNo Int
     * @param userId Long
     */
    @GET("user/{userId}/share_articles/{pageNo}/json")
    suspend fun getArticlesByUserId(
        @Path("pageNo") pageNo: Int,
        @Path("userId") userId: Long
    ): Response<UserArticles>


    /**
     * 10.3 查询自己分享的文章列表
     * @param pageSize Int
     * @param pageNo Int
     */
    @GET("user/lg/private_articles/{pageNo}/json")
    suspend fun getMySharedArticles(
        @Query("page_size") pageSize: Int,
        @Path("pageNo") pageNo: Int
    ): Response<UserArticles>

    /**
     * 10.4 删除自己分享的文章
     * @param id Long
     */
    @POST("lg/user_article/delete/{id}/json")
    @FormUrlEncoded
    suspend fun deleteMySharedArticle(@Path("id") id: Long)

    /**
     * 10.5 分享文章
     * @param title String
     * @param link String
     */
    @POST("lg/user_article/add/json")
    @FormUrlEncoded
    suspend fun shareArticle(@Field("title") title: String, @Field("link") link: String)

    /**
     * 11 问答列表
     * @param pageNo Int 从1开始
     * @param pageSize Int
     */
    @GET("wenda/list/{pageNo}/json")
    suspend fun getWendaList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int = 30
    ): Response<PagedData<Article>>


    /**
     * 12 个人信息接口
     * @return Response<UserInfoData>
     */
    @GET("user/lg/userinfo/json")
    suspend fun getUserInfo(): Response<UserInfoData>

    /**
     * 13 问答评论列表
     * @param id Long
     * @return Response<PagedData<Comment>>
     */
    @GET("wenda/comments/{id}/json")
    suspend fun getCommentsByWendaId(@Path("id") id: Long): Response<PagedData<Comment>>

    /**
     * 14.1 未读消息数量
     */
    @GET("message/lg/count_unread/json")
    suspend fun getUnreadMsgCount(): Response<Int>

    /**
     * 14.2 已读消息列表
     * @param pageNo Int
     * @param pageSize Int
     */
    @GET("message/lg/readed_list/{pageNo}/json")
    suspend fun getReadedMsgList(@Path("pageNo") pageNo: Int, @Query("page_size") pageSize: Int):Response<PagedData<Message>>

    /**
     * 14.3 未读消息列表，一旦调用默认已读
     * @param pageNo Int
     * @param pageSize Int
     * @return Response<PagedData<Message>>
     */
    @GET("message/lg/unread_list/{pageNo}/json")
    suspend fun getUnreadMsgList(@Path("pageNo")pageNo: Int,@Query("page_size")pageSize: Int):Response<PagedData<Message>>


    /**
     * 15.1 获取公众号列表
     * @return Response<List<WXOfficialAccount>>
     */
    @GET("wxarticle/chapters/json")
    suspend fun getWXOfficialAccountList():Response<List<WXOfficialAccount>>

    /**
     * 15.2 获取某个公众号历史数据
     * @param id Long
     * @param pageNo Int
     */
    @GET("wxarticle/list/{id}/{pageNo}/json")
    suspend fun getWxHistoryArticles(@Path("id")id:Long,@Path("pageNo")pageNo: Int):Response<PagedData<Article>>

    /**
     * 15.3 在某个公众号中搜索历史文章
     * @param id Long
     * @param pageNo Int
     * @param keyword String
     */
    @GET("wxarticle/list/{id}/{pageNo}/json?k={keyword}")
    suspend fun getWxArticleByKeyword(@Path("id")id:Long,@Path("pageNo")pageNo: Int,@Path("keyword")keyword: String)

    /**
     * 16.1 最新项目tab
     * @param pageNo Int
     * @return Response<PagedData<Article>>
     */
    @GET("article/listproject/{pageNo}/json")
    suspend fun getProjectList(@Path("pageNo")pageNo: Int):Response<PagedData<Article>>

    /**
     * 18.1 教程列表
     * @return Response<List<Tutorial>>
     */
    @GET("chapter/547/sublist/json")
    suspend fun getTutorialList():Response<List<Tutorial>>

    /**
     * 18.2 教程下文章列表
     * @param pageNo Int
     * @param cid Long
     * @return Response<PagedData<Article>>
     */
    @GET("article/list/{pageNo}/json")
    suspend fun getTutorialArticleList(@Path("pageNo")pageNo: Int,@Query("cid")cid:Long):Response<PagedData<Article>>

}