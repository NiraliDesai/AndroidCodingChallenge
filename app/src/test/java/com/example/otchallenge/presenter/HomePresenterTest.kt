package com.example.otchallenge.presenter

import com.example.otchallenge.base.BaseTest
import com.example.otchallenge.domain.model.BookModel
import com.example.otchallenge.domain.usecases.GetBookListUseCase
import com.example.otchallenge.util.network.NetworkUtils
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class HomePresenterTest : BaseTest() {

    @Mock
    private lateinit var mockView: HomeContract.View

    @Mock
    private lateinit var mockUseCase: GetBookListUseCase

    @Mock
    private lateinit var networkUtils: NetworkUtils

    private lateinit var sut: HomePresenter

    private val mockBooks = listOf(
        BookModel(
            description = "Description of Book 1",
            price = "10.99",
            title = "Book 1",
            author = "Author 1",
            bookImage = "https://example.com/book1.jpg",
            bookImageWidth = 200,
            bookImageHeight = 300
        ),
        BookModel(
            description = "Description of Book 2",
            price = "15.99",
            title = "Book 2",
            author = "Author 2",
            bookImage = "https://example.com/book2.jpg",
            bookImageWidth = 250,
            bookImageHeight = 350
        )
    )

    private val errorMessage = "Network Error"

    @Before
    override fun setUp() {
        super.setUp()  // Calls the setup in BaseTest

        mockView = mock(HomeContract.View::class.java)
        mockUseCase = mock(GetBookListUseCase::class.java)
        sut = HomePresenter(mockUseCase, networkUtils)

        sut.attachView(mockView)
    }

    @Test
    fun `should update view state when use case returns success`() = runTest {
        // Arrange
        val successResponse: Result<List<BookModel>> = Result.success(mockBooks)
        whenever(networkUtils.isNetworkAvailable()).thenReturn(true)
        whenever(mockUseCase.invoke()).thenReturn(successResponse)

        // Act
        sut.fetchBookData()
        advanceUntilIdle() // Ensure all coroutines have completed

        // Assert
        verify(mockView, times(1)).showLoading()
        verify(mockView, times(1)).showData(mockBooks)
        verify(mockView, times(1)).hideLoading() // Adjust to verify after coroutine finishes
    }

    @Test
    fun `should update view state with specific network error message`() = runTest {
        // Arrange
        val networkErrorMessage = "No internet connection. Please try again."
        whenever(networkUtils.isNetworkAvailable()).thenReturn(false)

        sut.fetchBookData()
        testDispatcher.scheduler.advanceUntilIdle()

        verify(mockView, times(1)).hideLoading()
        verify(mockView, times(1)).showError(networkErrorMessage) // Verify specific error message
    }


    @Test
    fun `should update view state when use case returns failure`() = runTest {
        // Arrange
        val failureResponse: Result<List<BookModel>> =
            Result.failure(RuntimeException(errorMessage))
        whenever(networkUtils.isNetworkAvailable()).thenReturn(true)
        whenever(mockUseCase.invoke()).thenReturn(failureResponse)

        //Act
        sut.fetchBookData()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        verify(mockView, times(1)).showLoading()
        verify(mockView, times(1)).hideLoading()
        verify(mockView, times(1)).showError(errorMessage)
    }
}
