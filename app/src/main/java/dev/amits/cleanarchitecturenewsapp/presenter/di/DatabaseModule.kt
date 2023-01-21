package dev.amits.cleanarchitecturenewsapp.presenter.di

import android.content.Context
import androidx.room.Room
import dev.amits.cleanarchitecturenewsapp.data.db.NewsDao
import dev.amits.cleanarchitecturenewsapp.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context):NewsDatabase{
        return Room.databaseBuilder(context , NewsDatabase::class.java , "newsDb").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase) : NewsDao {
        return database.newsDao()
    }

}