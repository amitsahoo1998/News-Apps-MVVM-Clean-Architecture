package dev.amits.cleanarchitecturenewsapp.data.repository

import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.CacheDataSource
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.LocalDataSource
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.OnlineDataSource
import dev.amits.cleanarchitecturenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl (private val cacheDataSource: CacheDataSource,
                          private val onlineDataSource: OnlineDataSource ,
                          private val localDataSource: LocalDataSource) : NewsRepository {

    private fun responseToResource(response : Response<NewsResponse> ): dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {result->
                return dev.amits.cleanarchitecturenewsapp.data.Resource.Success(result);
            }
        }
        return dev.amits.cleanarchitecturenewsapp.data.Resource.Error(response.message())
    }


    override suspend fun getHeadlines(page: Int, country: String): dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse> {
        return responseToResource(onlineDataSource.getNewsFromApi(page , country))
    }

    override suspend fun getSearchNews(
        country: String,
        searchQuery: String,
        page: Int
    ): dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse> {
        return responseToResource(onlineDataSource.getSearchNews(country, searchQuery, page))
    }

    override suspend fun saveNews(article: Article) {
        localDataSource.saveNewsDb(article)
    }

    override suspend fun deleteNews(article: Article) {
        localDataSource.deleteNewsDb(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return localDataSource.getAllNewsDb()
    }

}