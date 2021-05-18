package com.moviedb.core.gateways

import com.moviedb.core.entities.MovieDetailsEntity
import com.moviedb.core.entities.MovieEntity
import com.moviedb.core.entities.PopularMovies

interface MovieGateWay {
    fun loadPopularMovies(): Any
    suspend fun loadMovieDetails(movieId: Int): MovieDetailsEntity
}