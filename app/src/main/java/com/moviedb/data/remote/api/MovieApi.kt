package com.moviedb.data.remote.api

import com.moviedb.core.entities.MovieEntity
import com.moviedb.core.entities.PopularMovies
import com.moviedb.data.remote.ApiFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(ApiFactory.GET_POPULAR_MOVIES)
    suspend fun loadPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PopularMovies

    @GET(ApiFactory.GET_MOVIE_DETAILS)
    suspend fun loadMovieDetails(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movieId: Int
    ): MovieEntity
}
