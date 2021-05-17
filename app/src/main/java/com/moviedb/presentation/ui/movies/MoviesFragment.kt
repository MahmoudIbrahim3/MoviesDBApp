package com.moviedb.presentation.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moviedb.R
import com.moviedb.presentation.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection

class MoviesFragment : BaseFragment() {

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        twoPane = requireContext().resources.getBoolean(R.bool.isTwoPane)

        return when {
            twoPane ->
                inflater.inflate(R.layout.fragment_movies_land, container, false)
            else ->
                inflater.inflate(R.layout.fragment_movies, container, false)
        }
    }
}