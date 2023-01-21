package dev.amits.cleanarchitecturenewsapp.presenter.di

import dev.amits.cleanarchitecturenewsapp.data.db.NewsDao
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.LocalDataSource
import dev.amits.cleanarchitecturenewsapp.data.repository.datasourceimpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(newsDao: NewsDao) : LocalDataSource{
        return LocalDataSourceImpl(newsDao)
    }
}