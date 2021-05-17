package com.moviedb.data

import com.moviedb.core.usecases.LoadMovieDetailsUseCase
import com.moviedb.core.usecases.LoadPopularMoviesUseCase

data class Interactors(
    val loadPopularMoviesUseCase: LoadPopularMoviesUseCase,
    val loadMovieDetailsUseCase: LoadMovieDetailsUseCase
)