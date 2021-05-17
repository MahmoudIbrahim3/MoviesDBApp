package com.moviedb.presentation.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.moviedb.MainActivity
import com.moviedb.R
import com.moviedb.core.entities.MovieDetailsEntity
import com.moviedb.data.utils.DataResource
import com.moviedb.di.ViewModelFactory
import com.moviedb.presentation.ui.base.BaseFragment
import com.moviedb.presentation.utils.AppConst
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.toolbar
import kotlinx.android.synthetic.main.layout_screen_loading.*
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initMovieDetailsLiveData()
        hideActionBar()
        loadMovieDetails()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieDetailsViewModel::class.java)
    }

    private fun initMovieDetailsLiveData() {
        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is DataResource.Loading ->
                    startLoading(pbLoading)
                is DataResource.Success -> {
                    stopLoading(pbLoading)
                    renderData(it.value)
                }
                is DataResource.Failure -> {
                    stopLoading(pbLoading)
                    onLoadDataFailure(it.errorEntity)
                }
            }
        })
    }

    private fun hideActionBar() {
        (activity as MainActivity).hideActionBar()
    }

    private fun renderData(data: MovieDetailsEntity) {
        Glide.with(this)
            .load(getString(R.string.base_url_image) + data.backdrop_path)
            .into(ivBackImage)

        tvOverview.text = data.overview
        tvMovieTitle.text = data.title

        setUpActionBar()
    }

    private fun setUpActionBar() {
        toolbar.title = ""
        val act = (activity as MainActivity)
        act.setSupportActionBar(toolbar)
        act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            act.onBackPressed()
        }
    }

    /*private fun setUpAppBarTitle() {
        arguments?.let {
            val title = it.getString(AppConst.INTENT_TITLE)
            val act = (activity as MainActivity)

            if (requireContext().resources.getBoolean(R.bool.isTwoPane))
                act.setActionBarTitle(title, false)
            else
                act.setActionBarTitle(title, true)
        }
    }*/

    private fun loadMovieDetails() {
        arguments?.let {
            val movieId = it.getInt(AppConst.INTENT_ID)
            viewModel.loadMovieDetails(movieId)
        }
    }
}