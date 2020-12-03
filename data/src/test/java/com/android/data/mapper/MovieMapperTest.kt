package com.android.data.mapper

import com.android.data.response.MovieListResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieMapperTest {

    private lateinit var sut: MovieMapper
    private lateinit var response: MovieListResponse

    @Before
    fun setup() {
        sut = MovieMapper()
        response = MovieListResponse(
                page = 1,
                results = listOf(),
                totalPages = 2,
                totalResults = 20
        )
    }

    @Test
    fun `maps page`() {
        val result = sut.map(response)
        Assert.assertEquals(result.page, response.page)
    }

    @Test
    fun `maps results`() {
        val result = sut.map(response)
        Assert.assertEquals(result.results, response.results)
    }

    @Test
    fun `maps total pages`() {
        val result = sut.map(response)
        Assert.assertEquals(result.totalPages, response.totalPages)
    }

    @Test
    fun `maps total results`() {
        val result = sut.map(response)
        Assert.assertEquals(result.totalResults, response.totalResults)
    }

}