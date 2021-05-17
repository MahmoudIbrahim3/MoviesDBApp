package com.moviedb.presentation.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.moviedb.core.entities.MovieDetailsEntity
import com.moviedb.data.Interactors
import com.moviedb.data.utils.DataResource
import com.moviedb.data.utils.networkBoundResource
import com.moviedb.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val interactors: Interactors
) : BaseViewModel() {

    val movieDetailsLiveData = MutableLiveData<DataResource<MovieDetailsEntity>>()

    fun loadMovieDetails(movieId: Int) = viewModelScope.launch {
        networkBoundResource(movieDetailsLiveData) {
            interactors.loadMovieDetailsUseCase(movieId)
        }
    }
}