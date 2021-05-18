package com.moviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.moviedb.core.entities.MovieEntity
import com.moviedb.core.gateways.MovieGateWay
import com.moviedb.data.remote.api.MovieApi
import com.moviedb.presentation.utils.AppConst
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieApi: MovieApi): MovieGateWay {

    override fun loadPopularMovies(): Flow<PagingData<MovieEntity>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            MoviePagingSource(movieApi)
        }.flow
    }

    override suspend fun loadMovieDetails(movieId: Int) =
        movieApi.loadMovieDetails(
            movieId,
            AppConst.API_KEY
        )
}