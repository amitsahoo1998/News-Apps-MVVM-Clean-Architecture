package dev.amits.cleanarchitecturenewsapp.presenter.di

import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.CacheDataSource
import dev.amits.cleanarchitecturenewsapp.data.repository.datasourceimpl.CacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CacheDataSourceModule {

    @Provides
    @Singleton
    fun provideCacheDataSource():CacheDataSource{
        return CacheDataSourceImpl()
    }
}