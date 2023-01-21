package dev.amits.cleanarchitecturenewsapp.data.repository.datasourceimpl

import dev.amits.cleanarchitecturenewsapp.data.db.NewsDao
import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.data.repository.datasource.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalDataSourceImpl (private val newsDao: NewsDao) : LocalDataSource {
    override fun getAllNewsDb(): Flow<List<Article>> {
        return newsDao.getNews()
    }

    override suspend fun deleteNewsDb(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            newsDao.deleteNews(article)
        }
    }

    override suspend fun saveNewsDb(news: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            newsDao.saveNews(news)
        }
    }

}