package dev.amits.cleanarchitecturenewsapp.data.repository.datasource

import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import retrofit2.Response

interface OnlineDataSource {
    suspend fun getNewsFromApi ( page : Int , country : String ) : Response<NewsResponse>
    suspend fun getSearchNews (country: String , searchQuery : String , page : Int) : Response<NewsResponse>
}