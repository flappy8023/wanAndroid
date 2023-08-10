package com.flappy.wanandroid.di

import android.content.Context
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.db.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author flappy8023
 * @Description //TODO
 * @Date 2023年08月10日 16:13
 **/
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiManager.createService()
    }

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): RoomDB {
        return RoomDB.buildDataBase(context)
    }
}