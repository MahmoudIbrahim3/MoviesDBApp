package com.moviedb.data.repository

import com.moviedb.core.gateways.MovieGateWay
import com.moviedb.data.remote.api.MovieApi
import com.moviedb.presentation.utils.AppConst

class MovieRepository(private val movieApi: MovieApi): MovieGateWay {

    override suspend fun loadPopularMovies(page: Int) =
        movieApi.loadPopularMovies(
            AppConst.API_KEY,
            page
        )

    override suspend fun loadMovieDetails(movieId: Int) =
        movieApi.loadMovieDetails(
            AppConst.API_KEY,
            movieId
        )
}