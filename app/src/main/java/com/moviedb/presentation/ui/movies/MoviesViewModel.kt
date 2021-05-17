package com.moviedb.presentation.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.moviedb.core.entities.PopularMovies
import com.moviedb.data.Interactors
import com.moviedb.data.utils.DataResource
import com.moviedb.data.utils.networkBoundResource
import com.moviedb.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val interactors: Interactors
) : BaseViewModel() {

    val moviesLiveData = MutableLiveData<DataResource<PopularMovies>>()

    fun loadPopularMovies(page: Int) = viewModelScope.launch {
        networkBoundResource(moviesLiveData) {
            interactors.loadPopularMoviesUseCase(page)
        }
    }
}