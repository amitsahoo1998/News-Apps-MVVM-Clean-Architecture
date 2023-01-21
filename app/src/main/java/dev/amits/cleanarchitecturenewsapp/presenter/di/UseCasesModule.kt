package dev.amits.cleanarchitecturenewsapp.presenter.di

import dev.amits.cleanarchitecturenewsapp.domain.repository.NewsRepository
import dev.amits.cleanarchitecturenewsapp.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UseCasesModule {

    @Singleton
    @Provides
    fun provideGetNewsUseCases(newsRepository: NewsRepository):GetNewsUseCases{
        return GetNewsUseCases(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchNewsUseCases(
        newsRepository: NewsRepository
    ) : GetSearchNewsUseCases{
        return GetSearchNewsUseCases(newsRepository)
    }

    @Singleton
    @Provides
    fun provideSaveNewsUseCase(
        newsRepository: NewsRepository
    ):SaveNewsUseCases{
        return SaveNewsUseCases(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedNewsUseCases(
        newsRepository: NewsRepository
    ) : GetSavedNewsUseCases{
        return GetSavedNewsUseCases(newsRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSavedNewsUseCase(
        newsRepository: NewsRepository
    ):DeleteNewsUseCases{
        return DeleteNewsUseCases(newsRepository)
    }

}