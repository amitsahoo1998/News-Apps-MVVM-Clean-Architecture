package dev.amits.cleanarchitecturenewsapp.presenter.di

import android.content.Context
import dev.amits.cleanarchitecturenewsapp.data.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun createOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(context.cacheDir, cacheSize)

        val client = OkHttpClient.Builder()
            .cache(cache) //To use cache
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (dev.amits.cleanarchitecturenewsapp.BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addNetworkInterceptor(interceptor)
        }

        client.addNetworkInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }.build()

        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(dev.amits.cleanarchitecturenewsapp.BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit) : NewsService {
        return retrofit.create(NewsService::class.java)
    }
}