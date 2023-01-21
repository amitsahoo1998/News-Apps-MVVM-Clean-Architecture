package dev.amits.cleanarchitecturenewsapp.data.repository.datasourceimpl

import dev.amits.cleanarchitecturenewsapp.data.api.NewsService
import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.OnlineDataSource
import retrofit2.Response

class OnlineDataSourceImpl(private val newsService: NewsService) : OnlineDataSource {
    override suspend fun getNewsFromApi(page: Int, country: String): Response<NewsResponse> {
        return newsService.getHeading(country , page)
    }

    override suspend fun getSearchNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<NewsResponse> {
        return newsService.getSearchedTopHeadlines(country , searchQuery , page)
    }
}