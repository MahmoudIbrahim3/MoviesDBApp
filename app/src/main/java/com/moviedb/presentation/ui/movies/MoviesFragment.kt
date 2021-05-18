package com.moviedb.presentation.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.moviedb.MainActivity
import com.moviedb.R
import com.moviedb.core.entities.MovieEntity
import com.moviedb.core.entities.PopularMovies
import com.moviedb.data.utils.DataResource
import com.moviedb.di.ViewModelFactory
import com.moviedb.presentation.ui.base.BaseFragment
import com.moviedb.presentation.utils.AppConst
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    private var twoPane: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MovieAdapter

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        setupRecyclerView()
        initPopularMoviesLiveData()
        initOnMoviesClickLiveData()
        initSwipeToRefresh()
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        if(viewModel.moviesLiveData.value == null)
            viewModel.loadPopularMovies()
        else
            viewModel.moviesLiveData.value = viewModel.moviesLiveData.value
    }

    override fun onStart() {
        super.onStart()
        setUpActionBar()
    }

    private fun setUpActionBar() {
        val act = (activity as MainActivity)
        act.setUpActionBar()
        act.setActionBarTitle(getString(R.string.popular_movies), false)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MoviesViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter()
        rvItems.adapter = adapter.withLoadStateFooter(
            MovieLoadingAdapter { adapter.retry() }
        )
    }

    private fun initPopularMoviesLiveData() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            stopLoading(swipeToRefresh)
            renderData(it)
        })
    }

    private fun initOnMoviesClickLiveData() {
        adapter.onMovieClickedLiveData.observe(viewLifecycleOwner, Observer {
            val arg = setUpBundle(it)

            if (twoPane) {
                navigateToMovieDetailsFragment(arg)
            } else {
                findNavController().navigate(
                    R.id.action_moviesFragment_to_movieDetailsFragment2, arg
                )
            }
        })
    }

    private fun navigateToMovieDetailsFragment(arg: Bundle) {
        val navHostFragment = childFragmentManager.findFragmentById(
            R.id.nav_host_fragment_movie_details
        ) as NavHostFragment
        navHostFragment.navController.navigate(R.id.movieDetailsFragment, arg)
    }

    private fun renderData(data: PagingData<MovieEntity>) {
        lifecycleScope.launch {
            adapter.submitData(data)
            showFirstArticleForTwoPane()
        }
    }

    private fun showFirstArticleForTwoPane() {
        if (twoPane) {
            val arg = setUpBundle(adapter.snapshot().items[0])
            navigateToMovieDetailsFragment(arg)
        }
    }

    private fun setUpBundle(movieEntity: MovieEntity): Bundle {
        val arg = Bundle()
        arg.putInt(AppConst.INTENT_ID, movieEntity.id)
        arg.putString(AppConst.INTENT_TITLE, movieEntity.title)
        return arg
    }

    private fun initSwipeToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }
}