package com.moviedb.data.repository

import androidx.paging.PagingSource
import com.moviedb.core.entities.MovieEntity
import com.moviedb.data.remote.api.MovieApi
import com.moviedb.presentation.utils.AppConst
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(private val movieApi: MovieApi): PagingSource<String, MovieEntity>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, MovieEntity> {
        return try {
            val pageIndex = (params.key ?: 1).toString().toInt()

            val response = movieApi.loadPopularMovies(
                AppConst.API_KEY,
                pageIndex
            )
            val movies = response.results ?: listOf()
            return LoadResult.Page(
                movies,
                prevKey = if (pageIndex == 0) null else (pageIndex - 1).toString(),
                nextKey = if (movies.isEmpty()) null else (pageIndex + 1).toString()
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean = true
}