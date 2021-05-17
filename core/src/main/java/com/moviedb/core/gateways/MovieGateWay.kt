package com.moviedb.core.gateways

import com.moviedb.core.entities.MovieEntity
import com.moviedb.core.entities.PopularMovies

interface MovieGateWay {
    suspend fun loadPopularMovies(page: Int): PopularMovies
    suspend fun loadMovieDetails(movieId: Int): MovieEntity
}