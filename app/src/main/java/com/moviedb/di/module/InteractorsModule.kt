package com.moviedb.di.module

import com.moviedb.core.usecases.LoadMovieDetailsUseCase
import com.moviedb.core.usecases.LoadPopularMoviesUseCase
import com.moviedb.data.Interactors
import com.moviedb.data.repository.MovieRepository
import com.moviedb.data.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object InteractorsModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideInteractors(
        movieRepository: MovieRepository
    ) = Interactors(
        LoadPopularMoviesUseCase(movieRepository),
        LoadMovieDetailsUseCase(movieRepository)
    )

    @JvmStatic
    @Provides
    @Singleton
    fun bindMovieRepository(movieApi: MovieApi) =
        MovieRepository(movieApi)
}