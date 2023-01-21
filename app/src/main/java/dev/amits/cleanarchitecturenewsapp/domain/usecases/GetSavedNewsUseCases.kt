package dev.amits.cleanarchitecturenewsapp.domain.usecases

import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCases (private val newsRepository: NewsRepository) {

    fun execute() : Flow<List<Article?>> = newsRepository.getSavedNews()

}