package dev.amits.cleanarchitecturenewsapp.data.repository.datasource

import dev.amits.cleanarchitecturenewsapp.data.model.Article
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    // All News DB Operation
    fun getAllNewsDb() : Flow<List<Article>>
    suspend fun deleteNewsDb(article: Article)
    suspend fun saveNewsDb(news : Article)
}