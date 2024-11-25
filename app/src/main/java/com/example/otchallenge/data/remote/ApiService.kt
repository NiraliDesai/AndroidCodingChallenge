package com.example.otchallenge.data.remote

import com.example.otchallenge.data.remote.model.PublishedBookListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the API endpoints for fetching the book list
 */
interface ApiService {
    @GET("lists/current/hardcover-fiction.json")

    suspend fun getBooksList(
        @Query("api-key") apiKey: String,
        @Query("offset") offset: Int
    ): Response<PublishedBookListResponse?>
}
