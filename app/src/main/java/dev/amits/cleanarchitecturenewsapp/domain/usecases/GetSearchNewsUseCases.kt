package dev.amits.cleanarchitecturenewsapp.domain.usecases

import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import dev.amits.cleanarchitecturenewsapp.domain.repository.NewsRepository

class GetSearchNewsUseCases (private val newsRepository: NewsRepository){

    suspend fun execute(country: String , searchQuery : String , page: Int) : dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse> = newsRepository.getSearchNews(country, searchQuery, page)

}