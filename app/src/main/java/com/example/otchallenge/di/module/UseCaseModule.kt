package com.example.otchallenge.di.module

import com.example.otchallenge.domain.mapper.BookListResponseToModel
import com.example.otchallenge.domain.repository.BooksRepository
import com.example.otchallenge.domain.usecases.GetBookListUseCase
import dagger.Module
import dagger.Provides

/**
 * Dagger module for providing use case dependencies
 */
@Module
class UseCaseModule {

    @Provides
    fun provideGetBookListUseCase(
        booksRepository: BooksRepository,
        mapper: BookListResponseToModel
    ): GetBookListUseCase {
        return GetBookListUseCase(booksRepository, mapper)
    }
}