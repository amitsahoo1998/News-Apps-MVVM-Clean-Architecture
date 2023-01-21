package dev.amits.cleanarchitecturenewsapp.data.db

import androidx.room.*
import dev.amits.cleanarchitecturenewsapp.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(article: Article)

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM NewsTable")
    fun getNews() : Flow<List<Article>>
}