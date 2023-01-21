package dev.amits.cleanarchitecturenewsapp.data.repository.datasourceimpl

import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.CacheDataSource

class CacheDataSourceImpl :CacheDataSource {
    private var arrayListOfNews = ArrayList<Article>()
    override suspend fun getNewsFromCache(): List<Article> {
        return arrayListOfNews
    }

    override suspend fun updateNewsToCache(newsList: List<Article>) {
        arrayListOfNews.clear()
        arrayListOfNews = ArrayList(newsList)
    }
}