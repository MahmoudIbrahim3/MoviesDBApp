package com.moviedb.core.usecases

import com.moviedb.core.gateways.MovieGateWay

class LoadPopularMoviesUseCase(private val movieGateWay: MovieGateWay) {
    operator fun invoke() = movieGateWay.loadPopularMovies()
}

class LoadMovieDetailsUseCase(private val movieGateWay: MovieGateWay) {
    suspend operator fun invoke(movieId: Int) = movieGateWay.loadMovieDetails(movieId)
}