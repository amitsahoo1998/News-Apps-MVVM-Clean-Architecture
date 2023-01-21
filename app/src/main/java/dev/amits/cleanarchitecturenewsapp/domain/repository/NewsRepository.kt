package dev.amits.cleanarchitecturenewsapp.domain.repository

import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getHeadlines(page:Int , country:String) : dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse>
    suspend fun getSearchNews(country: String , searchQuery:String , page: Int) : dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews() : Flow<List<Article>>
}