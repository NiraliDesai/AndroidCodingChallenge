package com.example.otchallenge.domain.repository

import com.example.otchallenge.data.remote.model.PublishedBookListResponse
import retrofit2.Response

/**
 * Interface defining the contract for book repository
 */
interface BooksRepository {
    suspend fun getBooksList(): Response<PublishedBookListResponse?>
}