package dev.amits.cleanarchitecturenewsapp.domain.usecases

import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.domain.repository.NewsRepository

class SaveNewsUseCases(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}