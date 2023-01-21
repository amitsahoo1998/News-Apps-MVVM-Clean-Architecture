package dev.amits.cleanarchitecturenewsapp.presenter.di

import dev.amits.cleanarchitecturenewsapp.data.api.NewsService
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.OnlineDataSource
import dev.amits.cleanarchitecturenewsapp.data.repository.datasourceimpl.OnlineDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class OnlineDataSourceModule {

    @Provides
    @Singleton
    fun provideOnlineDataSource(newsService: NewsService) : OnlineDataSource{
        return OnlineDataSourceImpl(newsService)
    }
}