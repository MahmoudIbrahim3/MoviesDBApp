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
import com.moviedb.core.entities.Genres
import com.moviedb.core.entities.MovieDetailsEntity
import com.moviedb.data.utils.DataResource
import com.moviedb.di.ViewModelFactory
import com.moviedb.presentation.ui.base.BaseFragment
import com.moviedb.AppConst
import com.moviedb.presentation.utils.DateUtils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.toolbar
import kotlinx.android.synthetic.main.layout_screen_loading.*
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieDetailsViewModel
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
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(!twoPane) hideActionBar()
        initViewModel()
        initMovieDetailsLiveData()
        loadMovieDetails()
    }

    private fun hideActionBar() {
        (activity as MainActivity).hideActionBar()
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

    private fun loadMovieDetails() {
        arguments?.let {
            val movieId = it.getInt(AppConst.INTENT_ID)
            viewModel.loadMovieDetails(movieId)
        }
    }

    private fun renderData(data: MovieDetailsEntity) {

        Glide.with(this)
            .load(getString(R.string.base_url_image) + data.backdrop_path)
            .placeholder(R.drawable.image_banner_placeholder)
            .error(R.drawable.image_banner_placeholder)
            .into(ivBackImage)

        if(!data.poster_path.isNullOrEmpty()) {
            cvPosterImage.visibility = View.VISIBLE
            Glide.with(this)
                .load(getString(R.string.base_url_image) + data.poster_path)
                .error(R.drawable.image_poster_placeholder)
                .into(ivPosterImage)
        }

        tvMovieTitle.text = data.title

        tvOverViewLabel.visibility = View.VISIBLE
        tvOverview.text = data.overview

        data.release_date?.let {
            tvYear.text = DateUtils.fetchYear(it)
            tvDateAndGenres.text = DateUtils.formatDateForMovieDetails(it) + " • " +
                    parseRuntime(data.runtime) + "\n" +
                    fetchGenres(data.genres)
        }

        data.vote_average?.let {
            cpUserScore.visibility = View.VISIBLE
            tvScorePercentage.visibility = View.VISIBLE
            tvUserScoreLabel.visibility = View.VISIBLE

            val percent = (it * 10).toInt()
            cpUserScore.progress = percent
            tvScorePercentage.text = "$percent%"
        }

        if(!twoPane)
            setUpActionBar()
    }

    private fun parseRuntime(runtime: Int?): String {
        runtime?.let {
            val h = runtime / 60
            val m = runtime % 60

            return "${h}h ${m}m"
        }
        return ""
    }

    private fun fetchGenres(genres: List<Genres>?): String {
        genres?.let {
            var result = ""
            for(i in genres.indices) {
                result += genres[i].name
                if(i < genres.size - 1)
                    result += ", "
            }
            return result
        }
        return ""
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
}