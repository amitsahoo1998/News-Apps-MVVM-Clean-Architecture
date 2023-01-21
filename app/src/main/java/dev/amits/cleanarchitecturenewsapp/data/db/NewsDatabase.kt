package dev.amits.cleanarchitecturenewsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.amits.cleanarchitecturenewsapp.data.model.Article


@Database(entities = [Article::class] , exportSchema = false , version = 1)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}