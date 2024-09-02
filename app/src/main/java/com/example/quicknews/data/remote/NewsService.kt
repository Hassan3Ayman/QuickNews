package com.example.quicknews.data.remote

import com.example.quicknews.data.remote.dto.NewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("{category}/us.json")
    suspend fun getCategoryNews(
        @Path("category") category: String
    ): Response<NewsDto>
}