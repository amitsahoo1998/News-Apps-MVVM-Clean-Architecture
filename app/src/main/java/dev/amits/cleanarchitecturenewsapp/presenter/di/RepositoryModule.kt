package dev.amits.cleanarchitecturenewsapp.presenter.di

import dev.amits.cleanarchitecturenewsapp.data.repository.NewsRepositoryImpl
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.CacheDataSource
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.LocalDataSource
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.OnlineDataSource
import dev.amits.cleanarchitecturenewsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(cacheDataSource: CacheDataSource,onlineDataSource: OnlineDataSource , localDataSource: LocalDataSource) : NewsRepository{
        return NewsRepositoryImpl(cacheDataSource,onlineDataSource, localDataSource)
    }
}