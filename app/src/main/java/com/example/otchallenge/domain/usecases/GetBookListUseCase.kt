package com.example.otchallenge.domain.usecases

import com.example.otchallenge.domain.mapper.BookListResponseToModel
import com.example.otchallenge.domain.model.BookModel
import com.example.otchallenge.domain.repository.BooksRepository
import javax.inject.Inject

/**
 * Use case class for fetching a list of books
 */
class GetBookListUseCase @Inject constructor(
    private val booksRepository: BooksRepository,
    private val mapper: BookListResponseToModel
) {

    // Fetch the list of books and map it to domain models
    suspend operator fun invoke(): Result<List<BookModel>> {
        try {
            val response = booksRepository.getBooksList()

            if (response.isSuccessful) {
                val books = response.body()
                return Result.success(mapper.mapFrom(books))
            } else {
                return Result.failure(
                    Exception(
                        "Something went wrong. \n" +
                                " Please try again."
                    )
                )
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}