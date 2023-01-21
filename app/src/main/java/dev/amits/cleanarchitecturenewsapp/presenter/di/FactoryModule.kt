package dev.amits.cleanarchitecturenewsapp.presenter.di

import dev.amits.cleanarchitecturenewsapp.domain.usecases.*
import dev.amits.cleanarchitecturenewsapp.presenter.view.viewmodel.LiveNewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        getNewsHeadlineUseCase : GetNewsUseCases,
        deleteNewsUseCases: DeleteNewsUseCases,
        getSavedNewsUseCases: GetSavedNewsUseCases,
        getSearchNewsUseCases: GetSearchNewsUseCases,
        saveNewsUseCases: SaveNewsUseCases
    ) : LiveNewsViewModelFactory {
        return LiveNewsViewModelFactory(getNewsHeadlineUseCase,
            deleteNewsUseCases,
            getSavedNewsUseCases,
            getSearchNewsUseCases,
            saveNewsUseCases
        )
    }
}