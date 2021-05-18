package com.moviedb.presentation.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moviedb.core.entities.MovieEntity
import com.moviedb.data.Interactors
import com.moviedb.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val interactors: Interactors
) : BaseViewModel() {

    val moviesLiveData = MutableLiveData<PagingData<MovieEntity>>()

    fun loadPopularMovies() = viewModelScope.launch {
        val flow = interactors.loadPopularMoviesUseCase() as Flow<PagingData<MovieEntity>>
        flow.cachedIn(this).collectLatest {
            moviesLiveData.value = it
        }
    }
}