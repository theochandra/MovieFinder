package com.android.domain.usecase

import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.repository.MovieRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieListByQueryUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    private lateinit var sut: GetMovieListByQueryUseCase

    @Before
    fun setup() {
        sut = GetMovieListByQueryUseCase(movieRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get searched movie list`() = runBlockingTest {
        given(movieRepository.getMovieListByQuery(any(), any()))
            .willReturn(listOf())

        sut.execute("test", 1)

        verify(movieRepository).getMovieListByQuery(any(), any())
    }

}