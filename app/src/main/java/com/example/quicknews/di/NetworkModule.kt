package com.example.quicknews.di

import com.example.quicknews.data.remote.NewsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://saurav.tech/NewsAPI/top-headlines/category/"
    private const val TIMEOUT = 30L


    @Singleton
    @Provides
    fun provideNewsService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NewsService {
        return provideRetrofit(BASE_URL, okHttpClient, gsonConverterFactory)
            .create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        url: String,
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(loggingInterceptor)
        }.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .header(
                    "Accept",
                    "application/json"
                )
            val request = requestBuilder.build()
            return@addInterceptor chain.proceed(request)
        }
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Singleton
    @Provides
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

}