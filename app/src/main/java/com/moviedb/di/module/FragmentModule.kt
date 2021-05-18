package com.moviedb.di.module

import com.moviedb.presentation.ui.moviedetails.MovieDetailsFragment
import com.moviedb.presentation.ui.movies.MoviesFragment
import com.moviedb.presentation.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment
}