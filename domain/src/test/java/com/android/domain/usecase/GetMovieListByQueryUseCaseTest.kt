package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieListByQueryUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    private lateinit var sut: GetMovieListByQueryUseCase

    @Before
    fun setup() {
        sut = GetMovieListByQueryUseCase(movieRepository)
    }

    @Test
    fun `shows loading when start`() {
        runBlocking {
            `when`(movieRepository.getMovieListByQuery("test", 1))
                    .thenReturn(Result.Loading)

            val result = sut.execute("test", 1)

            verify(movieRepository).getMovieListByQuery("test", 1)
            Assert.assertEquals(result, Result.Loading)
        }
    }

    @Test
    fun `returns success result when success retrieving the movie list`() {
        runBlocking {
            val movieList = MovieList(
                    page = 1,
                    results = listOf(),
                    totalPages = 2,
                    totalResults = 20
            )

            `when`(movieRepository.getMovieListByQuery("test", 1))
                    .thenReturn(Result.Success(movieList))

            val result = sut.execute("test", 1)

            verify(movieRepository).getMovieListByQuery("test", 1)
            Assert.assertEquals(result, Result.Success(movieList))
        }
    }

    @Test
    fun `returns error result when failed retrieving the movie list`() {
        runBlocking {
            val throwable = Throwable()

            `when`(movieRepository.getMovieListByQuery("test", 1))
                    .thenReturn(Result.Error(404, "error occurred"))

            val result = sut.execute("test", 1)

            verify(movieRepository).getMovieListByQuery("test", 1)
            Assert.assertEquals(result, Result.Error(404, "error occurred"))
        }
    }

}