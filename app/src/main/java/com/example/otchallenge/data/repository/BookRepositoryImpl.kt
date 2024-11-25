package com.example.otchallenge.data.repository

import com.example.otchallenge.data.remote.ApiService
import com.example.otchallenge.data.remote.Constants.API_KEY
import com.example.otchallenge.data.remote.model.PublishedBookListResponse
import com.example.otchallenge.domain.repository.BooksRepository
import retrofit2.Response

/**
 * Implementation of the BooksRepository interface, responsible for fetching book data from the API
 */
class BooksRepositoryImpl(
    private val apiService: ApiService,
) : BooksRepository {
    override suspend fun getBooksList(): Response<PublishedBookListResponse?> {
        return apiService.getBooksList(API_KEY, 0)
    }
}



