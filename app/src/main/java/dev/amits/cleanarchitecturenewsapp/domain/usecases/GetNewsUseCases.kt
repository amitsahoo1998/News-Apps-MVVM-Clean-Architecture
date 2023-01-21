package dev.amits.cleanarchitecturenewsapp.domain.usecases

import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import dev.amits.cleanarchitecturenewsapp.domain.repository.NewsRepository

class GetNewsUseCases (private val repository: NewsRepository) {

    suspend fun execute (page:Int,country:String) : dev.amits.cleanarchitecturenewsapp.data.Resource<NewsResponse> = repository.getHeadlines(page, country)
}