package com.android.data.repository

import com.android.data.api.ServiceApi
import com.android.data.mapper.MovieMapper
import com.android.data.response.MovieListResponse
import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response.success

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    @Mock
    lateinit var serviceApi: ServiceApi
    @Mock
    lateinit var movieMapper: MovieMapper

    private lateinit var sut: MovieRepository

    @Before
    fun setup() {
        sut = MovieRepositoryImpl(serviceApi, movieMapper)
    }

    @Test
    fun `gets the movieList`() {
        runBlocking {
            given(serviceApi.getMovieListByQuery(any(), any())).willReturn(mock())
            sut.getMovieListByQuery("test", 1)
            verify(serviceApi).getMovieListByQuery(any(), any())
        }
    }

    @Test
    fun `maps the movieList`() {
        runBlocking {
            val movieListResponse = mock<MovieListResponse>()
            val response = success(movieListResponse)

            given(serviceApi.getMovieListByQuery(any(), any())).willReturn(response)
            given(movieMapper.map(any())).willReturn(mock())
            sut.getMovieListByQuery("test", 1)
            verify(movieMapper).map(eq(movieListResponse))
        }
    }

    @Test
    fun `returns mapped movieList`() {
        runBlocking {
            val movieListResponse = mock<MovieListResponse>()
            val response = success(movieListResponse)
            val mappedMovieList = mock<MovieList>()

            given(serviceApi.getMovieListByQuery(any(), any())).willReturn(response)
            given(movieMapper.map(any())).willReturn(mappedMovieList)

            val result = sut.getMovieListByQuery("test", 1)
            Assert.assertEquals(Result.Success(mappedMovieList), result)
        }
    }

//    @Test
//    fun `returns error on failure`() {
//        runBlocking {
//            val errorResponse =
//                    "{\n" +
//                            "  \"type\": \"error\",\n" +
//                            "  \"message\": \"What you were looking for isn't here.\"\n"
//            + "}"
//            val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
//            val mockResponse = Response.error<MovieListResponse>(400, errorResponseBody)
//
//            given(serviceApi.getMovieListByQuery(any(), any())).willReturn(mockResponse)
//            val result = sut.getMovieListByQuery("test", 1)
//            assert
//        }
//    }
}