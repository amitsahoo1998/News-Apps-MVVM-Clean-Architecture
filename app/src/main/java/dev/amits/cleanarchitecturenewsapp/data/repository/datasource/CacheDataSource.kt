package dev.amits.cleanarchitecturenewsapp.data.repository.datasource

import dev.amits.cleanarchitecturenewsapp.data.model.Article

interface CacheDataSource {
    suspend fun getNewsFromCache():List<Article>
    suspend fun updateNewsToCache(newsList: List<Article>)
}