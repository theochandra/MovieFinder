package com.android.data.repository

import com.android.data.api.TMDBApi
import com.android.data.mapper.MovieMapper
import com.android.data.response.MovieListResponse
import com.android.data.response.MovieResponse
import com.android.domain.Result
import com.android.domain.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response.success

class MovieRepositoryImplTest {

    private val tmdbApi = mock(TMDBApi::class.java)
    private var movieMapper = MovieMapper()
    private lateinit var sut: MovieRepository

    private val movieResponse = MovieResponse(
            id = 1,
            overview = "overview",
            popularity = 5.9,
            posterPath = "poster_path",
            releaseDate = "release_date",
            title = "title"
    )
    private val movieListResponse = MovieListResponse(
            page = 1,
            results = listOf(movieResponse),
            totalPages = 2,
            totalResults = 20
    )

    @Before
    fun setup() {
        sut = MovieRepositoryImpl(tmdbApi, movieMapper)
    }

    @Test
    fun `get movie list by query`() {
        runBlocking {
            val response = success(movieListResponse)
            val deferred = async { response }
            `when`(tmdbApi.getMovieListByQuery("test", 1))
                    .thenReturn(deferred)

            val mappedResponse = movieMapper.map(movieListResponse)
            val result = sut.getMovieListByQuery("test", 1)
            verify(tmdbApi).getMovieListByQuery("test", 1)
            Assert.assertEquals(Result.Success(mappedResponse), result)
        }
    }


}