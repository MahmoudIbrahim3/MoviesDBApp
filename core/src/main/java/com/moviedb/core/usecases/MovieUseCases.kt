package com.moviedb.core.usecases

import com.moviedb.core.gateways.MovieGateWay

class LoadPopularMoviesUseCase(private val movieGateWay: MovieGateWay) {
    suspend operator fun invoke(page: Int) = movieGateWay.loadPopularMovies(page)
}

class LoadMovieDetailsUseCase(private val movieGateWay: MovieGateWay) {
    suspend operator fun invoke(movieId: Int) = movieGateWay.loadMovieDetails(movieId)
}