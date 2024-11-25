package com.example.otchallenge.domain.mapper

import com.example.otchallenge.data.remote.model.PublishedBookListResponse
import com.example.otchallenge.domain.model.BookModel
import javax.inject.Inject

/**
 * Mapper class to convert API response models to domain models
 */
class BookListResponseToModel @Inject constructor() :
    Mapper<PublishedBookListResponse?, List<BookModel>> {

    override fun mapFrom(from: PublishedBookListResponse?): List<BookModel> {
        return from?.let {
            it.resultsBookList.books.map { book ->
                BookModel(
                    book.description,
                    book.price,
                    book.title,
                    book.author,
                    book.bookImage,
                    book.bookImageWidth,
                    book.bookImageHeight
                )
            }
        } ?: run {
            listOf()
        }

    }

}