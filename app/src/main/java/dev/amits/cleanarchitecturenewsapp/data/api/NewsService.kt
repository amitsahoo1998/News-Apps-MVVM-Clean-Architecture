package dev.amits.cleanarchitecturenewsapp.data.api


import dev.amits.cleanarchitecturenewsapp.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines")
    suspend fun getHeading(
        @Query("country")
        country: String ,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey : String = dev.amits.cleanarchitecturenewsapp.BuildConfig.API_KEY
    ) : Response<NewsResponse>


    @GET("/v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = dev.amits.cleanarchitecturenewsapp.BuildConfig.API_KEY
    ) : Response<NewsResponse>



}